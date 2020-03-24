package com.bryanbonner.yelpapp.app.data

import com.google.gson.annotations.SerializedName

data class Business(
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("price")
    val price: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @Transient
    var reviews: List<Review>? = emptyList()
)