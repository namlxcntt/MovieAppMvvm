package com.example.mvvmarchitectureappmovie.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitectureappmovie.data.model.MovieDetails2
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import com.example.mvvmarchitectureappmovie.data.repository.NetworkState
import com.example.mvvmarchitectureappmovie.ui.detailmovie.MoviesDetailRepository
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(private val movieRepository: MoviesDetailRepository, movieId: Int) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val movieDetails: LiveData<MoviesDetails> by lazy {
        movieRepository.fetchSingleMovieDetails(compositeDisposable, movieId)
    }
    val netWorkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}