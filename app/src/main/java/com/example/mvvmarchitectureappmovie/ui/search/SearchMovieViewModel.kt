package com.example.mvvmarchitectureappmovie.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import com.example.mvvmarchitectureappmovie.ui.detailmovie.MoviesDetailRepository
import io.reactivex.disposables.CompositeDisposable

class SearchMovieViewModel(private val searchRepository: SearchMovieRepository) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    fun setQuery(query2: String): LiveData<MovieResponse> {
        return searchRepository.fetchSearchMovie(compositeDisposable, query2)
    }

}