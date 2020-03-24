package com.bryanbonner.yelpapp.app.data


import com.google.gson.annotations.SerializedName

data class SearchBusinessesResult(
    @SerializedName("total")
    val total: Int? = 0,
    @SerializedName("businesses")
    val businesses: List<Business>
)