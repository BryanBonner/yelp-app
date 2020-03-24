package com.bryanbonner.yelpapp.app

import com.bryanbonner.yelpapp.app.di.networkModule
import com.bryanbonner.yelpapp.app.di.repositoryModule

fun configureAppComponent(baseUrl: String? = "/") = listOf(networkModule, repositoryModule)