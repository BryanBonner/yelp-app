package com.bryanbonner.yelpapp.app.data.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.bryanbonner.yelpapp.app.data.BusinessRepository
import com.bryanbonner.yelpapp.app.data.Business
import com.bryanbonner.yelpapp.app.service.NetworkState
import kotlinx.coroutines.*

class BusinessDataSource(
    private val repository: BusinessRepository,
    private val query: String?,
    private val location: String,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Business>() {

    private var supervisorJob = SupervisorJob()
    private val networkState = MutableLiveData<NetworkState>()
    private var retryQuery: (() -> Any)? = null

    private val pageSize = 10

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Business>
    ) {
        retryQuery = { loadInitial(params, callback) }
        executeSearchBusinesses(5, params.requestedLoadSize) {
            callback.onResult(it, null, pageSize)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Business>) {
        val page = params.key
        retryQuery = { loadAfter(params, callback) }
        executeSearchBusinesses(page, params.requestedLoadSize) {
            callback.onResult(it, page + pageSize)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Business>) {}

    private fun executeSearchBusinesses(page: Int, perPage: Int, callback: (List<Business>) -> Unit) {
        networkState.postValue(NetworkState.RUNNING)

        scope.launch(getJobErrorHandler() + supervisorJob) {
            val businesses = repository.searchBusinessesWithPagination(query, location, page, perPage)

            retryQuery = null
            networkState.postValue(NetworkState.SUCCESS)
            callback(businesses)
        }
    }

    //TODO: Should zip with searchBusinesses
    fun executeSearchReviews(id: String) {
        scope.launch {

        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, _ ->
        networkState.postValue(NetworkState.FAILED)
    }

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }

    fun getNetworkState(): LiveData<NetworkState> = networkState

    fun refresh() = this.invalidate()

    fun retryFailedQuery() {
        val prevQuery = retryQuery
        retryQuery = null
        prevQuery?.invoke()
    }
}