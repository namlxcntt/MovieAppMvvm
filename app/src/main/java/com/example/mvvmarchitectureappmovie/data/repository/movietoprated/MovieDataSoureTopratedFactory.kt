package com.example.mvvmarchitectureappmovie.data.repository.movietoprated

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.data.repository.moviedatasource.MovieDataSource
import io.reactivex.disposables.CompositeDisposable

class MovieDataSoureTopratedFactory(
    private val apiService: TheMovieDBInteface,
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