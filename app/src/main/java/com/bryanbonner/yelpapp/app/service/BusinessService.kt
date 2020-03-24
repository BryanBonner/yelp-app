package com.bryanbonner.yelpapp.app.service

import com.bryanbonner.yelpapp.app.data.SearchBusinessesResult
import com.bryanbonner.yelpapp.app.data.SearchReviewsResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BusinessService {

    @GET("v3/businesses/search")
    fun getBusinesses(
        @Query("location") location: String? = "oc",
        @Query("term") query: String? = "coffee",
        @Query("latitude") latitude: String? = null,
        @Query("longitude") longitude: String? = null,
        @Query("limit") limit: Int? = 50,
        @Query("offset") offset: Int? = 0
    ): Deferred<Response<SearchBusinessesResult>>

    @GET("v3/businesses/{id}/reviews")
    suspend fun getReviews(
        @Path("id") id: String
    ): Deferred<Response<SearchReviewsResult>>

}



