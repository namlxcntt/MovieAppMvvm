package com.example.mvvmarchitectureappmovie.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Genres
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GenresDataSource(
    private val apiService: TheMovieDBInteface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _genresMovie = MutableLiveData<Genres>()
    val genresMovie: LiveData<Genres>
        get() = _genresMovie

    fun fetchGenresMovie() {
        compositeDisposable.add(
            apiService
                .getGenres()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _genresMovie.postValue(it)

                }, {
                    Log.e("namlxcntt",it.message.toString())
                })
        )
    }

}