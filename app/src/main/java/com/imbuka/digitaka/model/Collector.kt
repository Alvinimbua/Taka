package com.imbuka.digitaka.model

import android.os.Parcelable
import com.imbuka.digitaka.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Collector(var imageId: Int, var name: String, var isfavorite: Boolean):Parcelable


//singleton object declaration which will help to get list of garbage collectors
object VacationSpots {


    //images ref
    private val images = arrayListOf(
        R.drawable.green, R.drawable.pestlab, R.drawable.salu,
        R.drawable.tumcleaners, R.drawable.virgin
    )


    //list of garbage collectors name
    private val collectorNames = arrayListOf(
        "Go Green cleaners", "Pestlab Hygiene Solution Providers", "Salu Solutions, Cleaning garbage Collecton",
    "TUM Cleaners", "Virgin Waste Management Limited"
    )

//    private val collectOrder = arrayListOf("Order","Order","Order","Order","Order","Order")

    //Returns the list of collector objects to be displayed in CollectorListFragment RecylerView

    var collectorList: ArrayList<Collector>? =null
    get() {

        if (field != null)  // backing 'field' refers to 'collectorList' property object
            return field

        field = ArrayList()
        for (i in images.indices) {

            val imageId = images[i]
            val collectorName = collectorNames[i]
//            val collectOrder = collectOrder[i]
            val collector = Collector(imageId,collectorName,false)
            field!!.add(collector)
        }

        return field

    }


    //will contain the 'favorite' marked cities to be displayed in FavoriteFragment recyclerView
    var favoriteCollectorList: MutableList<Collector> = mutableListOf()
}

