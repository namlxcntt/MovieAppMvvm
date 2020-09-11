package com.example.mvvmarchitectureappmovie.data.datasource.moviecomming

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInterface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieComingDataSoureFactory(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieComingDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource =
            MovieComingDataSource(
                apiService,
                compositeDisposable
            )
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}