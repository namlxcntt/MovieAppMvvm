package com.example.mvvmarchitectureappmovie.ui.discover.popular

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
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

class DetailPopularFragment : Fragment(R.layout.fragment_detail_popular) , OnItemClickListener {

    private lateinit var movieAdapterComing: PopularMoviePagedListAdapter
    private lateinit var movieRepository: MoviePagedListRepository
    private lateinit var viewModel: DiscoverMovieViewModel
    private val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapterComing = PopularMoviePagedListAdapter(requireContext(), this)
        movieRepository = MoviePagedListRepository(apiService)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)
        (activity as MainActivity).bottomBar.visibility = View.GONE
        recycleviewDetailPopular.addItemDecoration(LayoutMarginDecoration(3, 12))
        recycleviewDetailPopular.layoutManager = gridLayoutManager
        recycleviewDetailPopular.adapter = movieAdapterComing
        viewModel = getViewModel()
        viewModel.moviePagedList.observe(requireActivity(), Observer {
            movieAdapterComing.submitList(it)
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
                    movieRepository, null,
                    null
                ) as T
            }
        })[DiscoverMovieViewModel::class.java]
    }

    override fun onClickListen(id: Int) {

    }

}