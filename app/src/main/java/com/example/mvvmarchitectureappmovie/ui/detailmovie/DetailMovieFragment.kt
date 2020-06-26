package com.example.mvvmarchitectureappmovie.ui.detailmovie

import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_single.*
import java.text.NumberFormat
import java.util.*


class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {
    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MoviesDetailRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
        if (arguments != null) {
            var movieId: Int = requireArguments().getInt("key")
            movieRepository =
                MoviesDetailRepository(
                    apiService
                )
            (activity as MainActivity).bottomBar.visibility = View.GONE
            viewModel = getViewModel(movieId)
            viewModel.movieDetails.observe(requireActivity(), Observer {
                bindUI(it)
            })

        }

    }


    override fun onDestroy() {
        super.onDestroy()
    }

    private fun bindUI(it: MoviesDetails) {
        movie_release_date.text = it.releaseDate
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.voteCount.toString()
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