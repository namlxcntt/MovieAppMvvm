package com.example.mvvmarchitectureappmovie.ui.search

import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.POSTER_BASE_URL
import com.example.mvvmarchitectureappmovie.data.model.Movie
import com.example.mvvmarchitectureappmovie.data.model.ResultReviews
import com.example.mvvmarchitectureappmovie.data.model.Review
import kotlinx.android.synthetic.main.item_recycleview_layout.view.*
import kotlinx.android.synthetic.main.item_recycleview_reviews.view.*

class SearchMovieAdapter() : RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>() {
    private var list = ArrayList<Movie>()

    fun setList(listReview: ArrayList<Movie>) {
        removeList()
        list.addAll(listReview)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycleview_movie2, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val imageMovie = v.cv_iv_movie_poster
        private val tvMovie = v.cv_movie_title

        fun onBind(position: Int) {
            val movies = list[position]
            val moviewPosterURL = POSTER_BASE_URL + movies.posterPath
            Glide.with(itemView.context!!)
                .load(moviewPosterURL)
                .into(imageMovie)
            tvMovie.text = movies.title

        }
    }

    public fun removeList() {
        list.clear()
         notifyDataSetChanged()
    }
}