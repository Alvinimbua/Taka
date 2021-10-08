package com.imbuka.digitaka

import android.app.Application
import timber.log.Timber

class DigiTaka:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}