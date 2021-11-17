package com.imbuka.digitaka

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.imbuka.digitaka.collector.CollectorListFragment
import com.imbuka.digitaka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //firebase instance variables
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.activityMainToolbar)

        auth = Firebase.auth
        if (auth.currentUser == null) {
            //not signed in, launch the sign activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }

        binding.activityMainToolbar.setOnClickListener {
            onBackPressed()
        }

        //Get HostFragment and Nav Controller
        val navHostFrag =
            supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
        navController = navHostFrag.navController

        //define AppBarConfiguration
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        //connect toolbar with Nav Controller
        binding.activityMainToolbar.setupWithNavController(navController, appBarConfiguration)

    }

    public override fun onStart() {
        super.onStart()
        //check if user is signed in.
        if (auth.currentUser == null) {
            //not signed in, launch the sign in activity
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }
    }

//   public override fun onPause() {
//        super.onPause()
//    }
//
//   public override fun onResume() {
//        super.onResume()
//
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sign_out_menu -> {
                signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun signOut() {
        AuthUI.getInstance().signOut(this)
        startActivity(Intent(this, SignInActivity::class.java))
        finish()
    }

    companion object {
        private const val TAG = "MainActivity"
        const val MESSAGES_CHILD = "messages"
        const val ANONYMOUS = "anonymous"
    }
}