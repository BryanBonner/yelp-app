package com.bryanbonner.yelpapp.app

import android.app.Application
import com.bryanbonner.yelpapp.app.di.appComponent
import org.koin.android.ext.android.startKoin

open class YelpApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponent)
    }
}