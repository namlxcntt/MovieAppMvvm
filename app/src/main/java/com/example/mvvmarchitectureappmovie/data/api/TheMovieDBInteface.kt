package com.example.mvvmarchitectureappmovie.data.api

import com.example.mvvmarchitectureappmovie.data.model.MovieDetails2
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// https://api.themoviedb.org/3/movie/popular?api_key=15b4991eb81afbfaed1d581ba62c3b8b&language=en-US&page=1
//  https://api.themoviedb.org/3/movie/popular?api_key=15b4991eb81afbfaed1d581ba62c3b8b&language=en-US&page=1
//https://api.themoviedb.org/3/movie/top_rated?api_key=15b4991eb81afbfaed1d581ba62c3b8b&language=en-US&page=1
//https://api.themoviedb.org/3/movie/upcoming?api_key=15b4991eb81afbfaed1d581ba62c3b8b&language=en-US&page=1
interface TheMovieDBInteface {
    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") id: Int): Single<MoviesDetails>

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRateMovie(@Query("page") page: Int): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getCommingSoonMovie(@Query("page") page: Int): Single<MovieResponse>
}