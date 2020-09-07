package com.example.mvvmarchitectureappmovie.ui.detailmovie

import androidx.lifecycle.LiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import com.example.mvvmarchitectureappmovie.data.model.Review
import com.example.mvvmarchitectureappmovie.data.datasource.MovieDetailsNetworkDataSource
import com.example.mvvmarchitectureappmovie.data.model.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MoviesDetailRepository(private val apiService: TheMovieDBInteface) {
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

    fun fetchReviewMovies(compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<Review> {
        movieDetailsNetworkDataSource.fetchReviewMovies(movieId)
        return movieDetailsNetworkDataSource.reviewMovie
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}