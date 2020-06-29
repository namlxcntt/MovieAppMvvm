package com.example.mvvmarchitectureappmovie.data.model


import com.google.gson.annotations.SerializedName

data class Review(
    var id: Int,
    var page: Int,
    var results: List<ResultReviews>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)