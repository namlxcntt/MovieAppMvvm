package com.example.mvvmarchitectureappmovie.ui.genres

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.model.Genre
import kotlinx.android.synthetic.main.item_recycleview_genres.view.*

class GenresAdapter() : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private var list = ArrayList<Genre>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycleview_genres, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    public fun setList(list: ArrayList<Genre>) {
        this.list.clear()
        this.list = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var tvGenres = v.textViewGenres
        private var imageGenres = v.imageViewGenres
        fun onBind(position: Int) {
            val genre = list[position]
            tvGenres.text = genre.name
            when (genre.id) {
                28 -> Glide.with(itemView.context).load(R.drawable.image_action).into(imageGenres)
                12 -> Glide.with(itemView.context).load(R.drawable.image_adventure)
                    .into(imageGenres)
                16 -> Glide.with(itemView.context).load(R.drawable.image_animation)
                    .into(imageGenres)
                35 -> Glide.with(itemView.context).load(R.drawable.image_comedy).into(imageGenres)
                80 -> Glide.with(itemView.context).load(R.drawable.imge_crime)
                    .into(imageGenres)
                99 -> Glide.with(itemView.context).load(R.drawable.image_documentary)
                    .into(imageGenres)
                18 -> Glide.with(itemView.context).load(R.drawable.image_drama).into(imageGenres)
                10751 -> Glide.with(itemView.context).load(R.drawable.image_family)
                    .into(imageGenres)
                14 -> Glide.with(itemView.context).load(R.drawable.image_fantasy).into(imageGenres)
                36 -> Glide.with(itemView.context).load(R.drawable.image_history).into(imageGenres)
                27 -> Glide.with(itemView.context).load(R.drawable.image_hornor).into(imageGenres)
                10402 -> Glide.with(itemView.context).load(R.drawable.image_music)
                    .into(imageGenres)
                9648 -> Glide.with(itemView.context).load(R.drawable.image_mystery)
                    .into(imageGenres)
                10749 -> Glide.with(itemView.context).load(R.drawable.image_romatic)
                    .into(imageGenres)
                878 -> Glide.with(itemView.context).load(R.drawable.image_sciencefiction)
                    .into(imageGenres)
                10770 -> Glide.with(itemView.context).load(R.drawable.image_tvmovie)
                    .into(imageGenres)
                53 -> Glide.with(itemView.context).load(R.drawable.image_war).into(imageGenres)
                37 -> Glide.with(itemView.context).load(R.drawable.image_western).into(imageGenres)
            }


        }

    }
}