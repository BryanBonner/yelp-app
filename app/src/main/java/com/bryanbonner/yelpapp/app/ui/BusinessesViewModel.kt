package com.bryanbonner.yelpapp.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bryanbonner.yelpapp.app.data.BusinessRepository
import com.bryanbonner.yelpapp.app.data.paging.BusinessDataSourceFactory
import com.bryanbonner.yelpapp.app.service.NetworkState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class BusinessesViewModel(repository: BusinessRepository) : ViewModel() {

    //TODO:
    private val location = "Irvine"
    private val query = ""

    private val businessDataSource = BusinessDataSourceFactory(
        repository = repository,
        scope = CoroutineScope(Dispatchers.Default),
        query = query,
        location = location
    )

    val businesses = LivePagedListBuilder(businessDataSource, pagedListConfig()).build()

    val networkState: LiveData<NetworkState>? =
        switchMap(businessDataSource.dataSource) { it.getNetworkState() }

    fun getBusinessesByRepo(id: String) {

    }

    fun onSearchClicked(query: String, location: String) {
        businessDataSource.updateQuery(query, location)
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(50)
        .setEnablePlaceholders(false)
        .setPageSize(50)
        .build()

}
