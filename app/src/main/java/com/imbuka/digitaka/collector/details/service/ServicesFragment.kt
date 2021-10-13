package com.imbuka.digitaka.collector.details.service

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.imbuka.digitaka.R
import com.imbuka.digitaka.databinding.FragmentServicesBinding
import com.imbuka.digitaka.model.GarbageSpots
import timber.log.Timber

class ServicesFragment : Fragment() {

    private val binding:FragmentServicesBinding by lazy {
        FragmentServicesBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root
        // Inflate the layout for this fragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {

        val serviceAdapter = ServicesAdapter()


        binding.serviceRecyclerView.apply {
            adapter = serviceAdapter
            hasFixedSize()
        }

        Timber.e("Services: ${GarbageSpots.services}")

        serviceAdapter.submitList(GarbageSpots.services)
    }
}