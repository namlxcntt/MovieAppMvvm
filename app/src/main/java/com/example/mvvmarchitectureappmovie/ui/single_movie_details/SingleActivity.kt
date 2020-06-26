package com.example.mvvmarchitectureappmovie.ui.single_movie_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.POSTER_BASE_URL
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.data.model.MovieDetails2
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import com.example.mvvmarchitectureappmovie.data.repository.NetworkState
import com.example.mvvmarchitectureappmovie.ui.detailmovie.MoviesDetailRepository
import com.example.mvvmarchitectureappmovie.ui.detailmovie.SingleMovieViewModel
import kotlinx.android.synthetic.main.activity_single.*
import java.text.NumberFormat
import java.util.*

class SingleActivity : AppCompatActivity() {
    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MoviesDetailRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)
        val movieId: Int = intent.getIntExtra("id", 1)
        val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
        movieRepository =
            MoviesDetailRepository(
                apiService
            )
        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })
        viewModel.netWorkState.observe(this, Observer {
            progress_bar.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            txt_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
        })
    }

    private fun bindUI(it: MoviesDetails) {
        movie_release_date.text = it.releaseDate
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview
        val formarCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        val moviePosterURl = POSTER_BASE_URL + it.posterPath

        Glide.with(this)
            .load(moviePosterURl)
            .into(iv_movie_poster)
    }

    private fun getViewModel(movieID: Int): SingleMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SingleMovieViewModel(
                    movieRepository,
                    movieID
                ) as T
            }
        })[SingleMovieViewModel::class.java]
    }
}