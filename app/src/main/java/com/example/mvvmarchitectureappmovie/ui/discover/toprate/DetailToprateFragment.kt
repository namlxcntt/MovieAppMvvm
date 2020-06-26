package com.example.mvvmarchitectureappmovie.ui.discover.toprate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import com.example.mvvmarchitectureappmovie.ui.discover.DiscoverMovieViewModel
import com.example.mvvmarchitectureappmovie.utils.callback.OnItemClickListener
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail_popular.*
import kotlinx.android.synthetic.main.fragment_discover.*


class DetailToprateFragment : Fragment(R.layout.fragment_detail_toprate), OnItemClickListener {

    private lateinit var movieAdapterToprated: PopularMoviePagedTopratedListAdapter
    private lateinit var movieRepository: MoviePagedListTopratedRepository
    private lateinit var viewModel: DiscoverMovieViewModel
    private val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapterToprated = PopularMoviePagedTopratedListAdapter(requireContext(),this)
        movieRepository = MoviePagedListTopratedRepository(apiService)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        (activity as MainActivity).bottomBar.visibility = View.GONE
        recycleviewTopRated.addItemDecoration(LayoutMarginDecoration(3, 12))
        recycleviewTopRated.layoutManager = gridLayoutManager
        recycleviewTopRated.adapter = movieAdapterToprated
        viewModel = getViewModel()
        viewModel.moviePageListToprated.observe(requireActivity(), Observer {
            movieAdapterToprated.submitList(it)
        })
        buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getViewModel(): DiscoverMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return DiscoverMovieViewModel(
                    null, movieRepository, null

                ) as T
            }
        })[DiscoverMovieViewModel::class.java]
    }

    override fun onClickListen(id: Int) {
    }
}




