package com.bryanbonner.yelpapp.app.di

import com.bryanbonner.yelpapp.app.data.BusinessRepository
import org.koin.dsl.module.module

val repositoryModule = module {
    factory { BusinessRepository(get()) }
}