package com.imbuka.digitaka.collector.details.chat


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.imbuka.digitaka.*
import com.imbuka.digitaka.databinding.FragmentChatBinding
import com.imbuka.digitaka.model.FriendlyMessage
import timber.log.Timber

class ChatFragment : Fragment() {
    private val binding: FragmentChatBinding by lazy {
        FragmentChatBinding.inflate(layoutInflater)
    }
    private lateinit var manager: LinearLayoutManager

    //firebase instance variables
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseDatabase
    private lateinit var adapter: ChatAdapter

    private val openDocument = registerForActivityResult(MyOpenDocumentContract()) { uri ->
        onImageSelected(uri)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        db = Firebase.database
        val messageRef = db.reference.child(MESSAGES_CHILD)

        val options = FirebaseRecyclerOptions.Builder<FriendlyMessage>()
            .setQuery(messageRef, FriendlyMessage::class.java)
            .build()

        adapter = ChatAdapter(options, getUsername())
        binding.progressBar.visibility = ProgressBar.INVISIBLE
        manager = LinearLayoutManager(context)
        manager.stackFromEnd = true
        binding.messageRecyclerView.layoutManager = manager
        binding.messageRecyclerView.adapter = adapter

        //scroll down when a new messages arrives
        //see MyScrollToBottomObserver for details
        adapter.registerAdapterDataObserver(
            MyScrollToBottomObserver(binding.messageRecyclerView, adapter, manager)
        )

        binding.messageEditText.addTextChangedListener(MyButtonObserver(binding.sendButton))

        //when the send button is clicked send message
        binding.sendButton.setOnClickListener {
            val friendlyMessage = FriendlyMessage (
                binding.messageEditText.text.toString(),
                getUsername(),
                getPhotoUrl(),
                null
            )
            db.reference.child(MESSAGES_CHILD).push().setValue(friendlyMessage)
            binding.messageEditText.setText("")
        }

        //when the image button is clicked, launch the image picker
        binding.addMessageImageView.setOnClickListener {
            openDocument.launch(arrayOf("image/*"))
        }
    }

    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()
    }

    override fun onPause() {
        adapter.stopListening()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }

    private fun getPhotoUrl(): String? {
        val user = auth.currentUser
        return user?.photoUrl?.toString()
    }

    private fun getUsername(): String? {
        val user = auth.currentUser
        return if (user !=null) {
            user.displayName
        } else ANONYMOUS
    }


    private fun onImageSelected(uri: Uri) {
        Timber.d(TAG, "Uri: $uri")
        val user = auth.currentUser
        val tempMessage = FriendlyMessage(null, getUsername(), getPhotoUrl(), LOADING_IMAGE_URL)
        db.reference
            .child(MESSAGES_CHILD)
            .push()
            .setValue(
                tempMessage,
                DatabaseReference.CompletionListener { databaseError, databaseReference ->
                    if (databaseError !=null) {
                        Timber.w(TAG, "Unable to write Message to Database.",
                        databaseError.toException())
                        return@CompletionListener
                    }

                    //build a storage reference and then upload the file
                    val key = databaseReference.key
                    val storageReference = Firebase.storage
                        .getReference(user!!.uid)
                        .child(key!!)
                        .child(uri.lastPathSegment!!)
                    putImageInStorage(storageReference, uri, key)
                }
            )
    }

    private fun putImageInStorage(storageReference: StorageReference, uri: Uri, key: String?) {
        // Upload the image to Cloud Storage
        activity?.let {
            storageReference.putFile(uri)
                .addOnSuccessListener(it) { taskSnapshot -> //after the image loads, get a public downloadUrl for the
                    //image and add it to the message
                    taskSnapshot.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener { uri ->
                            val friendlyMessage =
                                FriendlyMessage(null, getUsername(), getPhotoUrl(), uri.toString())
                            db.reference
                                .child(MESSAGES_CHILD)
                                .child(key!!)
                                .setValue(friendlyMessage)
                        }
                }
                .addOnFailureListener(requireActivity()) { e ->
                    Timber.w(
                        TAG, "Image upload task was unsuccessful",
                        e
                    )
                }
        }
    }


    companion object {
        private const val TAG = "ChatFragment"
        const val MESSAGES_CHILD = "messages"
        const val ANONYMOUS = "anonymous"
        private const val LOADING_IMAGE_URL =  "https://www.google.com/images/spin-32.gif"

    }
}