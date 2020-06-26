package com.example.mvvmarchitectureappmovie.ui.detailmovie

import androidx.lifecycle.LiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.MovieDetails2
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import com.example.mvvmarchitectureappmovie.data.repository.MovieDetailsbNetworkDataSource
import com.example.mvvmarchitectureappmovie.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MoviesDetailRepository(private val apiService: TheMovieDBInteface) {
    lateinit var movieDetailsNetworkDataSource: MovieDetailsbNetworkDataSource

    fun fetchSingleMovieDetails(
        compositeDisposable: CompositeDisposable,
        movieId: Int
    ): LiveData<MoviesDetails> {
        movieDetailsNetworkDataSource =
            MovieDetailsbNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)
        return movieDetailsNetworkDataSource.downloadMovieDetails
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}