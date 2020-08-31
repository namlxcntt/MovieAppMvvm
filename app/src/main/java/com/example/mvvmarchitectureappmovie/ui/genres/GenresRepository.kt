package com.example.mvvmarchitectureappmovie.ui.genres

import androidx.lifecycle.LiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Genres
import com.example.mvvmarchitectureappmovie.data.model.GenresMovie
import com.example.mvvmarchitectureappmovie.data.repository.GenresDataSource
import io.reactivex.disposables.CompositeDisposable

class GenresRepository(private val apiService: TheMovieDBInteface) {
    private lateinit var genresDataSource: GenresDataSource

    fun fetchGenres(compositeDisposable: CompositeDisposable): LiveData<Genres> {
        genresDataSource = GenresDataSource(apiService, compositeDisposable)
        genresDataSource.fetchGenresMovie()
        return genresDataSource.genresMovie
    }
    fun fetchMovieWithGenres(compositeDisposable: CompositeDisposable,movieId : Int) : LiveData<GenresMovie>{
        genresDataSource = GenresDataSource(apiService, compositeDisposable)
        genresDataSource.fetchGenresMovie(movieId)
        return genresDataSource.genresMovieId
    }

}