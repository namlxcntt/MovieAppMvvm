package com.example.mvvmarchitectureappmovie.ui.genres

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.data.model.Genre
import com.example.mvvmarchitectureappmovie.ui.search.SearchMovieViewModel
import kotlinx.android.synthetic.main.fragment_genres.*

/**
 * A simple [Fragment] subclass.
 */
class GenresFragment : Fragment(R.layout.fragment_genres) {
    private lateinit var genresRepository: GenresRepository
    private lateinit var viewModel: GenresViewModel
    private lateinit var genresAdapter: GenresAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        genresAdapter = GenresAdapter()
        val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
        genresRepository = GenresRepository(apiService)
        viewModel = getViewModel()
        viewModel.fetchGenres().observe(requireActivity(), Observer {
            genresAdapter.setList(it.genres as ArrayList<Genre>)
        })
        recycleviewGenres.apply {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = genresAdapter
        }


    }

    private fun getViewModel(): GenresViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return GenresViewModel(
                    genresRepository
                ) as T
            }
        })[GenresViewModel::class.java]
    }

}
