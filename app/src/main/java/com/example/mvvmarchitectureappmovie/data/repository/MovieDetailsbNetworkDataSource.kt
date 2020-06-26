package com.example.mvvmarchitectureappmovie.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.model.MovieDetails2
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsbNetworkDataSource(
    private val apiService: TheMovieDBInteface,
    private val compositeDisposable: CompositeDisposable
) {

    private val _netWorkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _netWorkState

    private val _downloadMovieDetailsResponse = MutableLiveData<MoviesDetails>()
    val downloadMovieDetails: LiveData<MoviesDetails>
        get() = _downloadMovieDetailsResponse

    fun fetchMovieDetails(moviesId: Int) {
            _netWorkState.postValue(NetworkState.LOADING) //  Mutable live data post value  and networkState is enumClass Kotlin
            try {
                compositeDisposable
                    .add(
                        apiService.getMovieDetails(moviesId)
                            .subscribeOn(Schedulers.io())
                            /// Subcrie  first . success call api . next to throwable error
                            .subscribe({
                                _downloadMovieDetailsResponse.postValue(it)
                                _netWorkState.postValue(NetworkState.LOADED)

                            }, {
                                _netWorkState.postValue(NetworkState.ERROR)
                                Log.e("MoviesDetailsDataSource", it.message)
                            })
                    )


        } catch (error: Exception) {

        }

    }

}