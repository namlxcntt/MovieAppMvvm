package com.example.mvvmarchitectureappmovie.ui.genres

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmarchitectureappmovie.data.model.Genres
import io.reactivex.disposables.CompositeDisposable

class GenresViewModel(private val genresRepository: GenresRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    fun fetchGenres(): LiveData<Genres> {
        return genresRepository.fetchGenres(compositeDisposable)
    }
}