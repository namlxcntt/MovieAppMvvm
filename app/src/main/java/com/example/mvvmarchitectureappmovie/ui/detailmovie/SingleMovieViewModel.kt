package com.example.mvvmarchitectureappmovie.ui.detailmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import com.example.mvvmarchitectureappmovie.data.model.Review
import com.example.mvvmarchitectureappmovie.data.model.NetworkState
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
    val movieReviews : LiveData<Review> by lazy {
        movieRepository.fetchReviewMovies(compositeDisposable,movieId)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}