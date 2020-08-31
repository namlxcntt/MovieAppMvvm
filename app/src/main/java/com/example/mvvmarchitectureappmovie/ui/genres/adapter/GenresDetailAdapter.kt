package com.example.mvvmarchitectureappmovie.ui.genres.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.api.POSTER_BASE_URL
import com.example.mvvmarchitectureappmovie.data.model.Genre
import com.example.mvvmarchitectureappmovie.data.model.GenresMovie
import com.example.mvvmarchitectureappmovie.data.model.MoviesWithGenres
import kotlinx.android.synthetic.main.item_recycleview_movie2.view.*

class GenresDetailAdapter(var onItem :OnItemMovieDetailCallBack) : RecyclerView.Adapter<GenresDetailAdapter.ViewHolder>() {
    private var list = ArrayList<MoviesWithGenres>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var movieImage: ImageView = itemView.cv_iv_movie_poster
        private var movieTitle: TextView = itemView.cv_movie_title
        fun onBind(position: Int) {
            val movie: MoviesWithGenres = list[position]
            val moviewPosterURL = POSTER_BASE_URL + movie.posterPath
            Glide.with(itemView.context).load(moviewPosterURL).into(movieImage)
            movieTitle.text = movie.title
            itemView.setOnClickListener {
                onItem.onClickItemMovie(movie.id)
            }


        }

    }

    interface OnItemMovieDetailCallBack {
        fun onClickItemMovie(id: Int)
    }

    fun addItems(listItems: ArrayList<MoviesWithGenres>) {
        list.clear()
        list.addAll(listItems)
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

}