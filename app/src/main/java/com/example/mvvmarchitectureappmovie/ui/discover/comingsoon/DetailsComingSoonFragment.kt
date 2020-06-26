package com.example.mvvmarchitectureappmovie.ui.discover.comingsoon

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import com.example.mvvmarchitectureappmovie.ui.discover.DiscoverMovieViewModel
import com.example.mvvmarchitectureappmovie.utils.callback.OnItemClickListener
import com.oxcoding.moviemvvm.utils.MarginItemDecoration
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_details_coming_soon.*
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.fragment_discover.recycleviewComingsoon


class DetailsComingSoonFragment : Fragment(R.layout.fragment_details_coming_soon),
    OnItemClickListener {
    private lateinit var movieAdapterComing: PopularMovieComingPagedListAdapter
    private lateinit var movieRepositoryComing: MovieComingPagedListRepository
    private lateinit var viewModel: DiscoverMovieViewModel
    private val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
    private lateinit var navController: NavController
    private lateinit var toolbar : Toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        movieAdapterComing = PopularMovieComingPagedListAdapter(requireContext(), this)
        movieRepositoryComing = MovieComingPagedListRepository(apiService)
        val gridLayoutManager = GridLayoutManager(requireContext(), 3)

        (activity as MainActivity).bottomBar.visibility = View.GONE
        recycleviewComingsoon.addItemDecoration(LayoutMarginDecoration(3, 12))
        recycleviewComingsoon.layoutManager = gridLayoutManager
        recycleviewComingsoon.adapter = movieAdapterComing
        viewModel = getViewModel()
        viewModel.moviePageListComing.observe(requireActivity(), Observer {
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
                    null, null,
                    movieRepositoryComing
                ) as T
            }
        })[DiscoverMovieViewModel::class.java]
    }

    override fun onClickListen(id: Int) {
        val bundle = Bundle()
        bundle.putInt("key",id)
        navController.navigate(R.id.action_detailsComingSoonFragment_to_detailMovieFragment,bundle)

    }

}