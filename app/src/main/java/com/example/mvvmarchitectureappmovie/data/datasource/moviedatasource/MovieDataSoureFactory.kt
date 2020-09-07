package com.example.mvvmarchitectureappmovie.data.datasource.moviedatasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSoureFactory(
    private val apiService: TheMovieDBInteface,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource =
            MovieDataSource(
                apiService,
                compositeDisposable
            )
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}