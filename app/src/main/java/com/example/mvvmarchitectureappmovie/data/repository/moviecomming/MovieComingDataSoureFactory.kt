package com.example.mvvmarchitectureappmovie.data.repository.moviecomming

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.data.repository.moviedatasource.MovieDataSource
import io.reactivex.disposables.CompositeDisposable

class MovieComingDataSoureFactory(
    private val apiService: TheMovieDBInteface,
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