package com.bryanbonner.yelpapp.app.di

import com.bryanbonner.yelpapp.app.ui.BusinessesViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { BusinessesViewModel(get()) }
}