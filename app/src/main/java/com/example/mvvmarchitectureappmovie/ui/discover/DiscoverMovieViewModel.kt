package com.example.mvvmarchitectureappmovie.ui.discover

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.data.repository.NetworkState
import com.example.mvvmarchitectureappmovie.ui.discover.comingsoon.MovieComingPagedListRepository
import com.example.mvvmarchitectureappmovie.ui.discover.popular.MoviePagedListRepository
import com.example.mvvmarchitectureappmovie.ui.discover.toprate.MoviePagedListTopratedRepository
import io.reactivex.disposables.CompositeDisposable

class DiscoverMovieViewModel(
    private val movieRepository: MoviePagedListRepository?,
    private val movieRepositoryTop: MoviePagedListTopratedRepository?,
    private val movieRepositoryComing: MovieComingPagedListRepository?
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val moviePagedList: LiveData<PagedList<Movie>> by lazy {
        movieRepository!!.fetchLiveMoviePagedList(compositeDisposable)
    }
    val networkState: LiveData<NetworkState> by lazy {
        movieRepository!!.getNetworkState()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    val moviePageListToprated: LiveData<PagedList<Movie>> by lazy {
        movieRepositoryTop!!.fetchLiveMoviePagedListTop(compositeDisposable)
    }
    val moviePageListComing: LiveData<PagedList<Movie>> by lazy {
        movieRepositoryComing!!.fetchLiveMoviePagedList(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}