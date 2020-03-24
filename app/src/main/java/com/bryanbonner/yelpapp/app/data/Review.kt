package com.bryanbonner.yelpapp.app.data

import com.google.gson.annotations.SerializedName

data class Review(
    @SerializedName("rating")
    private val rating: Double,
    @SerializedName("text") val text: String,
    @SerializedName("time_created")
    private val timeCreated: String
)