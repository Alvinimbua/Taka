package com.imbuka.digitaka.utils

import android.content.Context
import android.widget.Toast

internal fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}