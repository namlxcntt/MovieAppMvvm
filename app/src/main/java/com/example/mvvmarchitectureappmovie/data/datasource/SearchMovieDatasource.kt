package com.example.mvvmarchitectureappmovie.data.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import io.reactivex.disposables.CompositeDisposable
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
                    .searchMovie(query)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        _searchMovie.postValue(it)
                    }, {
                        Log.d("xxxx", it.message.toString())
                    })
            )
        } catch (e: Exception) {
            Log.d("xxxx", e.message)

        }


    }
}