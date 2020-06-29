package com.example.mvvmarchitectureappmovie.ui.search

import androidx.lifecycle.LiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import com.example.mvvmarchitectureappmovie.data.repository.MovieDetailsbNetworkDataSource
import com.example.mvvmarchitectureappmovie.data.repository.SearchMovieDatasource
import io.reactivex.disposables.CompositeDisposable

class SearchMovieRepository(private val apiService: TheMovieDBInteface) {
    lateinit var movieDetailsNetworkDataSource: SearchMovieDatasource

    fun fetchSearchMovie(
        compositeDisposable: CompositeDisposable,
         query : String
    ) : LiveData<MovieResponse> {
        movieDetailsNetworkDataSource = SearchMovieDatasource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchSearchMovie(query)
        return movieDetailsNetworkDataSource.searchMovie

    }

}