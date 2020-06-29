package com.example.mvvmarchitectureappmovie.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import com.example.mvvmarchitectureappmovie.data.model.Review
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

 class SearchMovieDatasource(
    private val apiService: TheMovieDBInteface,
    private val compositeDisposable: CompositeDisposable
) {
    private val _searchMovie = MutableLiveData<MovieResponse>()
    val searchMovie: LiveData<MovieResponse>
        get() = _searchMovie

    fun fetchSearchMovie(query: String) {
        try {
            compositeDisposable.add(
                apiService
                    .searchMovie(query).subscribeOn(Schedulers.io()).subscribe({
                        _searchMovie.postValue(it)

                    }, {
                        Log.d("namxlctt", it.message.toString())
                    })
            )
        } catch (e: Exception) {
            Log.d("namlxcntt ", e.message)

        }


    }
}