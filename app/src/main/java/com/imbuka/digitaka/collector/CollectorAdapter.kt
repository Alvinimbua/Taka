package com.imbuka.digitaka.collector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imbuka.digitaka.R
import com.imbuka.digitaka.databinding.ListItemCollectorBinding
import com.imbuka.digitaka.model.Collector
import com.imbuka.digitaka.utils.toast


typealias OnCollector = (Collector) -> Unit

class CollectorAdapter(
    private val onClick: OnCollector
) :
    ListAdapter<Collector, CollectorAdapter.CollectorViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ListItemCollectorBinding.inflate(inflater, parent, false)
        return CollectorViewHolder(binding)

    }

    override fun onBindViewHolder(collectorVieHolder: CollectorViewHolder, position: Int) {

        collectorVieHolder.bind(getItem(position))
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Collector>() {
            override fun areItemsTheSame(oldItem: Collector, newItem: Collector): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Collector, newItem: Collector): Boolean {
                return oldItem == newItem
            }

        }
    }

    inner class CollectorViewHolder(
        private val binding: ListItemCollectorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Collector) {
            binding.apply {
                txtCollectorName.text = item.name
                imvCollector.setImageResource(item.imageId)

                if (item.isfavorite) imvFavorite.setImageResource(R.drawable.ic_favorite_filled) else R.drawable.ic_favorite_bordered

                root.setOnClickListener {
                    onClick.invoke(item)
                }

                imvFavorite.setOnClickListener {
                    root.context.toast("This Feature Is Coming Soon")
                }
                imvDelete.setOnClickListener {
                    root.context.toast("This Feature is Coming Soon")
                }

            }
        }


    }
}