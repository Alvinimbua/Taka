package com.imbuka.digitaka.collector


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imbuka.digitaka.databinding.FragmentCollectorListBinding
import com.imbuka.digitaka.model.VacationSpots

class CollectorListFragment : Fragment() {
    private val binding: FragmentCollectorListBinding by lazy {
        FragmentCollectorListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val collectorAdapter = CollectorAdapter { collector ->
            val action = CollectorListFragmentDirections.actionFragmentCollectorListToCollectorDetailsFragment(collector)
            findNavController().navigate(action)
        }

        binding.collectorRecyclerView.apply {
            adapter = collectorAdapter
            hasFixedSize()
        }

        collectorAdapter.submitList(VacationSpots.collectorList)
    }

}
