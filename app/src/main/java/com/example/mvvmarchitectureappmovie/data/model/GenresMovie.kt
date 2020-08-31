package com.example.mvvmarchitectureappmovie.data.model


import com.google.gson.annotations.SerializedName

data class GenresMovie(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    var moviesWithGenres: List<MoviesWithGenres>,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("total_results")
    var totalResults: Int
)