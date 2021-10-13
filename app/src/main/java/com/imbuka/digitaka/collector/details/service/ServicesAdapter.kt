package com.imbuka.digitaka.collector.details.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imbuka.digitaka.R
import com.imbuka.digitaka.databinding.ListItemCollectorBinding
import com.imbuka.digitaka.databinding.ListServicesBinding
import com.imbuka.digitaka.model.Services


class ServicesAdapter: ListAdapter<Services, ServicesAdapter.ServicesViewHolder>(diffUtil) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServicesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ListServicesBinding.inflate(inflater, parent, false)
        return ServicesViewHolder(binding)
    }

    override fun onBindViewHolder(serviceViewHolder: ServicesViewHolder, position: Int) {
        serviceViewHolder.bind(getItem(position))
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Services>() {
            override fun areItemsTheSame(oldItem: Services, newItem: Services): Boolean {
                return oldItem.typeOfService == newItem.typeOfService
            }

            override fun areContentsTheSame(oldItem: Services, newItem: Services): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ServicesViewHolder(
        private val binding: ListServicesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Services) {
            binding.apply {
                //replace with service type image
                serviceTypeImage.setImageResource(R.drawable.green)
                txtServiceType.text = item.typeOfService
                serviceDaysText.text = item.dayOfOperation
                timestampTextview.text = item.timestamp
            }
        }
    }



}
