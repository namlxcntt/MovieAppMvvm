package com.example.mvvmarchitectureappmovie.data.datasource.movietoprated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInterface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSoureTopratedFactory(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSourceTopRated>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource =
            MovieDataSourceTopRated(
                apiService,
                compositeDisposable
            )
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}