package com.example.mvvmarchitectureappmovie.ui.search

import androidx.lifecycle.LiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInterface
import com.example.mvvmarchitectureappmovie.data.datasource.SearchMovieDatasource
import com.example.mvvmarchitectureappmovie.data.model.MovieResponse
import io.reactivex.disposables.CompositeDisposable

class SearchMovieRepository(private val apiService: TheMovieDBInterface) {
    private lateinit var movieDetailsNetworkDataSource: SearchMovieDatasource

    fun fetchSearchMovie(
        compositeDisposable: CompositeDisposable,
         query : String
    ) : LiveData<MovieResponse> {
        movieDetailsNetworkDataSource = SearchMovieDatasource(apiService,compositeDisposable)
        movieDetailsNetworkDataSource.fetchSearchMovie(query)
        return movieDetailsNetworkDataSource.searchMovie

    }

}