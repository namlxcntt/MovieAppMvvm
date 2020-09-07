package com.example.mvvmarchitectureappmovie.ui.discover.comingsoon

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.mvvmarchitectureappmovie.data.api.POST_PER_PAGE
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.data.model.NetworkState
import com.example.mvvmarchitectureappmovie.data.datasource.moviecomming.MovieComingDataSource
import com.example.mvvmarchitectureappmovie.data.datasource.moviecomming.MovieComingDataSoureFactory
import io.reactivex.disposables.CompositeDisposable

class MovieComingPagedListRepository (private val apiService : TheMovieDBInteface) {

    private lateinit var moviePagedList: LiveData<PagedList<Movie>>
    private lateinit var moviesDataSourceFactory: MovieComingDataSoureFactory
    fun fetchLiveMoviePagedList (compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        moviesDataSourceFactory =
            MovieComingDataSoureFactory(
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
        return Transformations.switchMap<MovieComingDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieComingDataSource::networkState)
    }
}