package com.example.mvvmarchitectureappmovie.ui.genres

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
import com.example.mvvmarchitectureappmovie.data.model.Genre
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import com.example.mvvmarchitectureappmovie.ui.genres.adapter.GenresAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_genres.*

/**
 * A simple [Fragment] subclass.
 */
class GenresFragment : Fragment(R.layout.fragment_genres), GenresAdapter.OnItemClickGenres {
    private lateinit var genresRepository: GenresRepository
    private lateinit var viewModel: GenresViewModel
    private lateinit var genresAdapter: GenresAdapter
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        genresAdapter =
            GenresAdapter(
                this
            )
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
        (activity as MainActivity).bottomBar.visibility = View.VISIBLE



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

    override fun itemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt("key",id)
        navController.navigate(R.id.action_second_fragment_to_detailGenresFragment,bundle)
    }

}
