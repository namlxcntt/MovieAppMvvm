package com.example.mvvmarchitectureappmovie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Genre
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GenresDataSource(
    private val apiService: TheMovieDBInteface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _genresMovie = MutableLiveData<List<Genre>>()
    val genresMovie: LiveData<List<Genre>>
        get() = _genresMovie

    fun fetchGenresMovie() {
        compositeDisposable.add(
            apiService
                .getGenres()
                .subscribeOn(Schedulers.io())
                .subscribe({

                }, {

                })
        )
    }

}