package com.bryanbonner.yelpapp.app.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.bryanbonner.yelpapp.app.data.BusinessRepository
import com.bryanbonner.yelpapp.app.data.Business
import kotlinx.coroutines.CoroutineScope

class BusinessDataSourceFactory(
    private val repository: BusinessRepository,
    private var query: String,
    private var location: String,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Business>() {
    val dataSource = MutableLiveData<BusinessDataSource>()

    override fun create(): DataSource<Int, Business> {
        val source = BusinessDataSource(repository, query, location, scope)
        dataSource.postValue(source)
        return source
    }

    fun getSource() = dataSource.value

    fun updateQuery(query: String?, location: String) {
        if (query != null) {
            this.query = query
        }
        this.location = location
        getSource()?.refresh()
    }

}