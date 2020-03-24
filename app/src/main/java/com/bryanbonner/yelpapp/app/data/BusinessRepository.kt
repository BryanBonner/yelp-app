package com.bryanbonner.yelpapp.app.data

import com.bryanbonner.yelpapp.app.service.BusinessService


class BusinessRepository(private val service: BusinessService) {

    private suspend fun getBusinesses(
        location: String,
        query: String?,
        page: Int,
        perPage: Int
    ) = service.getBusinesses(location, query, null, null, perPage, page).await()

    suspend fun getReviews(businessId: String) = service.getReviews(businessId).await()

    //TODO: Error Handling
    suspend fun searchBusinessesWithPagination(query: String?, location: String, page: Int, perPage: Int) : List<Business> {
        val getBusinessRequest = getBusinesses(location, query, page, perPage)

        if (getBusinessRequest.isSuccessful) {
            return getBusinessRequest.body()?.businesses!!
        }
        return emptyList()
    }

}
