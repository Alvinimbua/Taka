package com.imbuka.digitaka.collector


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.imbuka.digitaka.R

class CollectorListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_collector_list, container, false)

        setupRecyclerView(view)

        return view
    }

    private fun setupRecyclerView(view: View?) {

        val context = requireContext()


        val collectorAdapter = CollectorAdapter(context, VacationSpots.collectorList!!)

        val recyclerView = view?.findViewById<RecyclerView>(R.id.collector_recycler_view)

        //set the adapter
        recyclerView?.adapter = collectorAdapter
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        recyclerView?.layoutManager = layoutManager



    }

}
