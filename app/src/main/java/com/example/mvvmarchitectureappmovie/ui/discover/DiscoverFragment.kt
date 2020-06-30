package com.example.mvvmarchitectureappmovie.ui.discover

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import com.example.mvvmarchitectureappmovie.ui.discover.comingsoon.MovieComingPagedListRepository
import com.example.mvvmarchitectureappmovie.ui.discover.comingsoon.PopularMovieComingPagedListAdapter
import com.example.mvvmarchitectureappmovie.ui.discover.popular.MoviePagedListRepository
import com.example.mvvmarchitectureappmovie.ui.discover.popular.PopularMoviePagedListAdapter
import com.example.mvvmarchitectureappmovie.ui.discover.toprate.MoviePagedListTopratedRepository
import com.example.mvvmarchitectureappmovie.ui.discover.toprate.PopularMoviePagedTopratedListAdapter
import com.example.mvvmarchitectureappmovie.utils.callback.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_discover.*

/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : Fragment(R.layout.fragment_discover), View.OnClickListener,
    OnItemClickListener {
    private lateinit var viewModel: DiscoverMovieViewModel
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var layoutManagerTop: LinearLayoutManager
    private lateinit var layoutManagerComing: LinearLayoutManager
    private lateinit var movieAdapter: PopularMoviePagedListAdapter
    private lateinit var movieAdapterTop: PopularMoviePagedTopratedListAdapter
    private lateinit var movieAdapterComing: PopularMovieComingPagedListAdapter
    lateinit var movieRepository: MoviePagedListRepository
    lateinit var movieRepositoryTop: MoviePagedListTopratedRepository
    private lateinit var navController: NavController


    lateinit var movieRepositoryComing: MovieComingPagedListRepository
    private val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).bottomBar.visibility = View.VISIBLE

        navController = Navigation.findNavController(view)
        setupUi()
        viewModel.moviePagedList.observe(requireActivity(), Observer {
            movieAdapter.submitList(it)
        })
        viewModel.moviePageListToprated.observe(requireActivity(), Observer {
            movieAdapterTop.submitList(it)
        })
        viewModel.moviePageListComing.observe(requireActivity(), Observer {
            movieAdapterComing.submitList(it)
        })

    }

    private fun setupUi() {
        movieRepository = MoviePagedListRepository(apiService)
        movieRepositoryTop = MoviePagedListTopratedRepository(apiService)
        movieRepositoryComing = MovieComingPagedListRepository(apiService)
        movieAdapter = PopularMoviePagedListAdapter(requireContext(), this)
        movieAdapterTop = PopularMoviePagedTopratedListAdapter(requireContext(), this)
        movieAdapterComing = PopularMovieComingPagedListAdapter(requireContext(), this)
        viewModel = getViewModel()

        recycleviewPopular.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = movieAdapter
        }
        recycleviewTopRated.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = movieAdapterTop
        }
        recycleviewComingsoon.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = movieAdapterComing
        }
        textViewSeeAllCommingSoon.setOnClickListener(this)
        textviewSeeAllPopular.setOnClickListener(this)
        textViewSeeAllTop.setOnClickListener(this)
        buttonSearch.setOnClickListener(this)
    }


    private fun getViewModel(): DiscoverMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return DiscoverMovieViewModel(
                    movieRepository, movieRepositoryTop, movieRepositoryComing
                ) as T
            }
        })[DiscoverMovieViewModel::class.java]
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.textViewSeeAllCommingSoon -> {
                navController!!.navigate(R.id.action_first_fragment_to_detailsComingSoonFragment)
            }
            R.id.textviewSeeAllPopular -> {
                navController!!.navigate(R.id.action_first_fragment_to_detailPopularFragment)
            }
            R.id.textViewSeeAllTop -> {
                navController!!.navigate(R.id.action_first_fragment_to_detailToprateFragment)
            }
            R.id.buttonSearch -> {
                navController!!.navigate(R.id.action_first_fragment_to_searchFragment)

            }
        }
    }

    override fun onClickListen(id: Int) {
        val bundle = Bundle()
        bundle.putInt("key", id)
        navController.navigate(R.id.action_first_fragment_to_detailMovieFragment, bundle)
    }
}

//        viewModel.networkState.observe(this, Observer {
//            progress_bar_popular.visibility =
//                if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
//            txt_error_popular.visibility =
//                if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE
//
//            if (!viewModel.listIsEmpty()) {
//                movieAdapter.setNetworkState(it)
//            }
//        })

