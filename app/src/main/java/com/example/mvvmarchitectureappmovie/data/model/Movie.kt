package com.example.mvvmarchitectureappmovie.data.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String
) : Serializable