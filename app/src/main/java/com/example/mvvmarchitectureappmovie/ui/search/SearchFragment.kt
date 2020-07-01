package com.example.mvvmarchitectureappmovie.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.annotation.MainThread
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import com.example.mvvmarchitectureappmovie.ui.detailmovie.MoviesDetailRepository
import com.example.mvvmarchitectureappmovie.ui.detailmovie.SingleMovieViewModel
import com.example.mvvmarchitectureappmovie.utils.callback.OnItemClickListener
import com.example.mvvmarchitectureappmovie.utils.snackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(R.layout.fragment_search), OnItemClickListener {
    private lateinit var viewModel: SearchMovieViewModel
    private lateinit var searchMovieRepository: SearchMovieRepository
    private lateinit var searchAdapter: SearchMovieAdapter
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
        (activity as MainActivity).bottomBar.visibility = View.GONE
        searchAdapter = SearchMovieAdapter(this)
        searchMovieRepository = SearchMovieRepository(apiService)
        viewModel = getViewModel()
        setUpUi()


    }

    private fun setUpUi() {
        recycleviewSearch.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = searchAdapter
            visibility = View.GONE
        }
        btnClickSearch.setOnClickListener {
            viewModel.setQuery(edtMoviesName.text.toString())
            viewModel.setQuery(edtMoviesName.text.toString()).observe(requireActivity(), Observer {
                if (it.movieList.isNotEmpty()) {
                    searchAdapter.setList(it.movieList as ArrayList<Movie>)
                    gif_deadpool.visibility = View.GONE
                    recycleviewSearch.visibility = View.VISIBLE
                } else {
                    searchAdapter.removeList()
                    gif_deadpool.visibility = View.VISIBLE
                    requireView().snackbar("No Result Search")
                }

            })
        }

        buttonBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun getViewModel(): SearchMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SearchMovieViewModel(
                    searchMovieRepository
                ) as T
            }
        })[SearchMovieViewModel::class.java]
    }

    override fun onClickListen(id: Int) {
        val bundle = Bundle()
        bundle.putInt("key", id)
        navController.navigate(R.id.action_searchFragment_to_detailMovieFragment,bundle)

    }

}