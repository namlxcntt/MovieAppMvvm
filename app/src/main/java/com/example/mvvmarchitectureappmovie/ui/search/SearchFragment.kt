package com.example.mvvmarchitectureappmovie.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInteface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.ui.detailmovie.MoviesDetailRepository
import com.example.mvvmarchitectureappmovie.ui.detailmovie.SingleMovieViewModel
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment(R.layout.fragment_search) {
    private lateinit var viewModel: SearchMovieViewModel
    private lateinit var searchMovieRepository: SearchMovieRepository
    private lateinit var searchAdapter: SearchMovieAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val apiService: TheMovieDBInteface = TheMovieDbClient.getClient()
        searchAdapter = SearchMovieAdapter()
        searchMovieRepository = SearchMovieRepository(apiService)
//        viewModel = getViewModel("avenger")
        recycleviewSearch.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = searchAdapter
            visibility = View.GONE
        }
        btnClickSearch.setOnClickListener {
            viewModel = getViewModel(edtMoviesName.text.toString())
            viewModel.searchMovieResult.observe(requireActivity(), Observer {
                searchAdapter.setList(it.movieList)
                recycleviewSearch.visibility = View.VISIBLE
            })
            viewModel.searchMovieResult.removeObservers(viewLifecycleOwner);        }
//        edtMoviesName.setOnEditorActionListener { p0, actionId, p2 ->
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//
//            }
//            false
//        }


    }

    private fun getViewModel(query: String): SearchMovieViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SearchMovieViewModel(
                    searchMovieRepository
                    ,
                    query
                ) as T
            }
        })[SearchMovieViewModel::class.java]
    }

}