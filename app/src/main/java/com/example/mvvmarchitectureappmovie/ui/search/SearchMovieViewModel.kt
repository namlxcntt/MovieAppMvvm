package com.example.mvvmarchitectureappmovie.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import com.example.mvvmarchitectureappmovie.ui.detailmovie.MoviesDetailRepository
import io.reactivex.disposables.CompositeDisposable

class SearchMovieViewModel(private val searchRepository: SearchMovieRepository, query: String) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val searchMovieResult :LiveData<MovieResponse> by lazy {
        searchRepository.fetchSearchMovie(compositeDisposable,query)
    }

}