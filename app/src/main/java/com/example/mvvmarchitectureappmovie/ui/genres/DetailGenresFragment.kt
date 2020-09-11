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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDBInterface
import com.example.mvvmarchitectureappmovie.data.api.TheMovieDbClient
import com.example.mvvmarchitectureappmovie.data.model.MoviesWithGenres
import com.example.mvvmarchitectureappmovie.ui.MainActivity
import com.example.mvvmarchitectureappmovie.ui.genres.adapter.GenresDetailAdapter
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail_genres.*


class DetailGenresFragment : Fragment(R.layout.fragment_detail_genres),
    GenresDetailAdapter.OnItemMovieDetailCallBack {
    private lateinit var genresRepository: GenresRepository
    private lateinit var viewModel: GenresViewModel
    private var genresAdapter: GenresDetailAdapter = GenresDetailAdapter(this)
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val apiService: TheMovieDBInterface = TheMovieDbClient.getClient()
        genresRepository = GenresRepository(apiService)
        viewModel = getViewModel()
        if (arguments != null) {
            val id: Int = requireArguments().getInt("key")
            recycleviewDetailGenres.apply {
                layoutManager = GridLayoutManager(requireContext(), 3)
                addItemDecoration(LayoutMarginDecoration(3, 12))
                adapter = genresAdapter
            }
            viewModel.fetGenres(id).observe(viewLifecycleOwner, Observer {
                genresAdapter.addItems(it.moviesWithGenres as ArrayList<MoviesWithGenres>)
            })
            (activity as MainActivity).bottomBar.visibility = View.GONE
            when (id) {
                1771 -> tvTitle.text = resources.getString(R.string.Action)
                385103 -> tvTitle.text = resources.getString(R.string.Adventure)
                703771 -> tvTitle.text = resources.getString(R.string.Animation)
                495764 -> tvTitle.text = resources.getString(R.string.Comedy)
                38700 -> tvTitle.text = resources.getString(R.string.Crime)
                703771 -> tvTitle.text = resources.getString(R.string.Documentary)
                632618 -> tvTitle.text = resources.getString(R.string.Drama)
                385103 -> tvTitle.text = resources.getString(R.string.Family)
                508439 -> tvTitle.text = resources.getString(R.string.Fantasy)
                531876 -> tvTitle.text = resources.getString(R.string.History)
                581392 -> tvTitle.text = resources.getString(R.string.Music)
                297762 -> tvTitle.text = resources.getString(R.string.Horror)
                446893 -> tvTitle.text = resources.getString(R.string.Science)
                703771 -> tvTitle.text = resources.getString(R.string.TV_Movie)
                613504 -> tvTitle.text = resources.getString(R.string.Thriller)
                107706 -> tvTitle.text = resources.getString(R.string.War)
                539885 -> tvTitle.text = resources.getString(R.string.Western)
                else -> tvTitle.text = "Genres"
            }
        }
        button_Back.setOnClickListener {
            requireActivity().onBackPressed()
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

    override fun onClickItemMovie(id: Int) {
        val bundle = Bundle()
        bundle.putInt("key",id)
        navController.navigate(R.id.action_detailGenresFragment_to_detailMovieFragment,bundle)
    }
}