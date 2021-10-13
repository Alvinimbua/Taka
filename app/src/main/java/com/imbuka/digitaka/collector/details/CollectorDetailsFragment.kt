package com.imbuka.digitaka.collector.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.imbuka.digitaka.collector.details.chat.ChatFragment
import com.imbuka.digitaka.databinding.FragmentCollectorDetailsBinding
import timber.log.Timber

class CollectorDetailsFragment : Fragment() {

    private val binding: FragmentCollectorDetailsBinding by lazy {
        FragmentCollectorDetailsBinding.inflate(layoutInflater)
    }

    val args: CollectorDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collector = args.collector

        (requireActivity() as AppCompatActivity).supportActionBar?.title = collector.name

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        binding.apply {
            collectorImage.setImageResource(collector.imageId)
            collectorName.text = collector.name
            viewPager.adapter = adapter
            TabLayoutMediator(tabLayout, viewPager){tab,position ->
                when(position){
                    0 ->{
                       tab.text = "Services"
                    }
                    1 ->{
                       tab.text = "Histories"
                    }
                }
            }.attach()

        }

        Timber.e("Collector Name: ${collector.name}")
    }

}