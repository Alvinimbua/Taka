package com.imbuka.digitaka.model

import android.os.Parcelable
import com.imbuka.digitaka.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Services(
    var typeOfService: String,
    var offer: String,
    var mission: String,
    var timeOfOperation: String
) : Parcelable

object GarbageSpots {
//    //list of garbage collectors name
//    private val collectorNames = arrayListOf(
//        "Go Green cleaners", "Pestlab Hygiene Solution Providers", "Salu Solutions, Cleaning garbage Collecton",
//        "TUM Cleaners", "Virgin Waste Management Limited"
//    )

    //list of services offered by garbage collectors
    private var services : MutableList<Services> = mutableListOf(
        Services(
            "Door to door collection",
            "10% discount on Saturdays",
            "We treasure your trash",
            "24 hours support - Mon-friday"
        ),
        Services(
            "Door to door collection",
            "10% discount on Saturdays",
            "We treasure your trash",
            "24 hours support - Mon-friday"
        ),

        Services(
            "Door to door collection",
            "10% discount on Saturdays",
            "We treasure your trash",
            "24 hours support - Mon-friday"
        ),

        Services(
            "Door to door collection",
            "10% discount on Saturdays",
            "We treasure your trash",
            "24 hours support - Mon-friday"
        )

//        "Door to door collection\n, 10% discounts on Friday\n, We are dedicated to service\n, 24Hours support",
//        "Door to door collection\n, 10% discounts on Friday\n, We are dedicated to service\n, 24Hours support",
//        "Door to door collection\n, 10% discounts on Friday\n, We are dedicated to service\n, 24Hours support"
    )

    var serviceList: List<Services>? = null
        get() {
            if (field != null)
                return field

            field = ArrayList()
            for (i in services.indices) {
                print(i)
            }
            return field
        }
}