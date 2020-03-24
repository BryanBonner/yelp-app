package com.bryanbonner.yelpapp.app.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("profile_url")
    private val profileUrl: String,
    @SerializedName("name")
    private val name: String
)