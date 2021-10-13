package com.imbuka.digitaka.model

import android.os.Parcelable
import com.imbuka.digitaka.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Services(
    var typeOfService: String,
    var timestamp: String,
    var dayOfOperation: String
) : Parcelable

object GarbageSpots {
    var services : MutableList<Services> = mutableListOf(
        Services(
            "Door to door collection",
            "13pm",
            "Mon-friday"
        ),
        Services(
            "Door to door collection",
            "12pm",
            "Mon-friday"
        ),

        Services(
            "Door to door collection",
            "8am",
            "Mon-friday"
        ),

        Services(
            "Door to door collection",
            "11am",
            "Mon-friday"
        )
    )

}