package com.example.mvvmarchitectureappmovie.ui.discover.toprate

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mvvmarchitectureappmovie.data.api.POST_PER_PAGE
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.data.repository.moviedatasource.MovieDataSource
import com.example.mvvmarchitectureappmovie.data.repository.moviedatasource.MovieDataSoureFactory
import com.example.mvvmarchitectureappmovie.data.repository.NetworkState
import com.example.mvvmarchitectureappmovie.data.repository.movietoprated.MovieDataSourceTopRated
import com.example.mvvmarchitectureappmovie.data.repository.movietoprated.MovieDataSoureTopratedFactory
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListTopratedRepository (private val apiService : TheMovieDBInteface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var moviesDataSourceFactory: MovieDataSoureTopratedFactory
    fun fetchLiveMoviePagedListTop (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory =
            MovieDataSoureTopratedFactory(
                apiService,
                compositeDisposable
            )

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSourceTopRated, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSourceTopRated::networkState)
    }
}