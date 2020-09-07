package com.example.mvvmarchitectureappmovie.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Genres
import com.example.mvvmarchitectureappmovie.data.model.GenresMovie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class GenresDataSource(
    private val apiService: TheMovieDBInteface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _genresMovie = MutableLiveData<Genres>()
    val genresMovie: LiveData<Genres>
        get() = _genresMovie
    private val _genresMovieId = MutableLiveData<GenresMovie>()
    val genresMovieId: LiveData<GenresMovie>
        get() = _genresMovieId


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

    fun fetchGenresMovie(movieId: Int) {
        try {
            compositeDisposable.add(
                apiService
                    .getGenresMovie(movieId).subscribeOn(Schedulers.io()).subscribe({
                        _genresMovieId.postValue(it)

                    }, {
                        Log.d("namxlctt", it.message.toString())
                    })
            )
        } catch (e: Exception) {
            Log.d("namlxcntt ", e.message)

        }

    }

}