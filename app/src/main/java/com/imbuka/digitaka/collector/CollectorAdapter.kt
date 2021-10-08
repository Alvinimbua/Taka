package com.imbuka.digitaka.collector

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.compose.material.Button
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.imbuka.digitaka.GreenActivity
import com.imbuka.digitaka.R

//make it a constructor by adding ()
class CollectorAdapter(val context: Context, var collectorList: ArrayList<Collector>) :
    RecyclerView.Adapter<CollectorAdapter.CollectorViewHolder>() {


    //purpose is to create the required view holder objects
    //which will be used to display items on screen
    //it will create a limited number of view objects which will be used to display items which will be recycled
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        //converting layout into a view object
        val itemView =
            LayoutInflater.from(context).inflate(R.layout.list_item_collector, parent, false)
        return CollectorViewHolder(itemView)
    }


    //set data for each item in the list
    //pos re the current item for which the method is being called
    override fun onBindViewHolder(collectorVieHolder: CollectorViewHolder, position: Int) {
        val collector = collectorList[position]
        collectorVieHolder.setData(collector, position)

    }


    //return size of collector list object
    override fun getItemCount(): Int = collectorList.size


    //using inner keyword so that the collectorViewHolder can use the properties of Collector Adapter class
    //create a primary constructor matching a super class
    inner class CollectorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, GreenActivity::class.java)
                itemView.context.startActivity(intent)

            }
        }


        private var currentPosition: Int = -1
        private var currentCollector: Collector? = null

        //initialize view objects
        private val txtCollectorName = itemView.findViewById<TextView>(R.id.txt_collector_name)
        private val imvCollectorImage = itemView.findViewById<ImageView>(R.id.imv_collector)
        private val imvDelete = itemView.findViewById<ImageView>(R.id.imv_delete)
        private val imvFavorite = itemView.findViewById<ImageView>(R.id.imv_favorite)
        private val btnRequest = itemView.findViewById<Button>(R.id.btn_request)


        //referencing to drawable resource
        private val icFavoriteFilledImage = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_favorite_filled, null
        )
        private val icFavoriteBorderedImage = ResourcesCompat.getDrawable(
            context.resources,
            R.drawable.ic_favorite_bordered, null
        )

        fun setData(collector: Collector, position: Int) {

            txtCollectorName.text = collector.name
            imvCollectorImage.setImageResource(collector.imageId)
//            txtRequest.text = collector.order

            if (collector.isfavorite)
                imvFavorite.setImageDrawable(icFavoriteFilledImage)
            else
                imvFavorite.setImageDrawable(icFavoriteBorderedImage)

            this.currentCollector = collector
            this.currentPosition = position


        }

        override fun onClick(v: View?) {

            when (adapterPosition) {
                0 -> {
                    /* go to go green activity intent */
                   itemView.setOnClickListener {
                       val intent = Intent(itemView.context, GreenActivity::class.java)
                       itemView.context.startActivity(intent)
                   }


                }
                1 -> {
                    //go to pestlab
                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, PestlabActivity::class.java)
                        itemView.context.startActivity(intent)
                    }


                }
                2 -> {
                    //go to salu

                }
                3 -> {
                    //go to tum cleaners

                }
                4 -> {
                    //go to virgin

                }
            }


        }


    }
}