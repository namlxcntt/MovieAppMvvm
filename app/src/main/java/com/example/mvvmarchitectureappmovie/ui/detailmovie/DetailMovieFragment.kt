package com.example.mvvmarchitectureappmovie.ui.detailmovie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.POSTER_BASE_URL
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.data.model.Genre
import com.example.mvvmarchitectureappmovie.data.model.MoviesDetails
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import com.hedgehog.ratingbar.RatingBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import java.lang.StringBuilder
import java.text.NumberFormat
import java.util.*


class DetailMovieFragment : Fragment(R.layout.fragment_detail_movie) {
    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MoviesDetailRepository
    private lateinit var ratingBar: RatingBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
        if (arguments != null) {
            var movieId: Int = requireArguments().getInt("key")
            movieRepository =
                MoviesDetailRepository(
                    apiService
                )
            (activity as MainActivity).bottomBar.visibility = View.GONE
            ratingBar = requireView().findViewById(R.id.ratingbar)
            viewModel = getViewModel(movieId)
            viewModel.movieDetails.observe(requireActivity(), Observer {
                bindUI(it)
            })

        }
        buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun bindUI(it: MoviesDetails) {
        tvTitle.text = it.title.trim()
//        movie_genres.text = it.tagline.trim()
        movie_time.text = "Times : " + it.runtime.toString() + " minutes" .trim()
        ratingBar.setStar((it.voteCount / 20).toFloat())
        movie_vote.text = it.voteCount.toString() + " Votes".trim()
        movie_overview.text = it.overview.trim()
        movie_language.text = "Language : " + it.originalLanguage.toUpperCase().trim()
        val formarCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        val moviePosterURl = POSTER_BASE_URL + it.posterPath
        val genres: List<Genre> = it.genres
        val stringGenres = StringBuilder()
        stringGenres.append(genres[0].name)
        for (i in 1..(genres.size - 1)) {
            stringGenres.append(" | ${genres[i].name}")
        }
        movie_genres.text = stringGenres.trim()

        Glide.with(this)
            .load(moviePosterURl)
            .into(iv_movie_poster)
        Glide.with(this)
            .load(moviePosterURl)
            .into(imageViewvarta)

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