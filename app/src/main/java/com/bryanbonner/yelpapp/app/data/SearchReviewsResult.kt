package com.bryanbonner.yelpapp.app.data

import com.google.gson.annotations.SerializedName

data class SearchReviewsResult(
    @SerializedName("reviews")
    val reviews: List<Review>,
    @SerializedName("user")
    val user: User
)