package com.example.mvvmarchitectureappmovie.ui.detailmovie

import android.net.Network
import androidx.lifecycle.LiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInterface
import com.example.mvvmarchitectureappmovie.data.datasource.MovieDetailsNetworkDataSource
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import com.example.mvvmarchitectureappmovie.data.model.NetworkState
import com.example.mvvmarchitectureappmovie.data.model.Review

import io.reactivex.disposables.CompositeDisposable

class MoviesDetailRepository(private val apiService: TheMovieDBInterface) {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MoviesDetails> {
        movieDetailsNetworkDataSource =
            MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)
        return movieDetailsNetworkDataSource.downloadMovieDetails
    }

    fun fetchReviewMovies(movieId: Int) : LiveData<Review> {
        movieDetailsNetworkDataSource.fetchReviewMovies(movieId)
        return movieDetailsNetworkDataSource.reviewMovie
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}