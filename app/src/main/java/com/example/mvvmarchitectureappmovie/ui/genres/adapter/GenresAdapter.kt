package com.example.mvvmarchitectureappmovie.ui.genres.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.model.Genre
import kotlinx.android.synthetic.main.item_recycleview_genres.view.*

class GenresAdapter(private val onItemClickGenres: OnItemClickGenres) :
    RecyclerView.Adapter<GenresAdapter.ViewHolder>() {
    private var list = ArrayList<Genre>()
    var listener: OnItemClickGenres = onItemClickGenres


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

    interface OnItemClickGenres {
        fun itemClick(id: Int)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var tvGenres = v.textViewGenres
        private var imageGenres = v.imageViewGenres
        fun onBind(position: Int) {
            val genre = list[position]
            tvGenres.text = genre.name
            itemView.setOnClickListener {
                listener.itemClick(1771)
            }
            when (genre.id) {
                28 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(1771)
                    }
                    Glide.with(itemView.context).load(R.drawable.image_action).into(imageGenres)
                }
                12 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(385103)
                    }
                    Glide.with(itemView.context).load(R.drawable.image_adventure)
                        .into(imageGenres)
                }

                16 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(703771)
                    }
                    Glide.with(itemView.context).load(R.drawable.image_animation)
                        .into(imageGenres)
                }
                35 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(495764)
                    }
                    Glide.with(itemView.context).load(R.drawable.image_comedy).into(imageGenres)
                }
                80 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(38700)
                    }
                    Glide.with(itemView.context).load(R.drawable.imge_crime)
                        .into(imageGenres)
                }
                99 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(703771)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_documentary)
                        .into(imageGenres)
                }
                18 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(632618)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_drama).into(imageGenres)
                }
                10751 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(385103)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_family)
                        .into(imageGenres)
                }
                14 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(508439)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_fantasy).into(imageGenres)
                }
                36 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(531876)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_history).into(imageGenres)
                }
                27 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(581392)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_hornor).into(imageGenres)
                    itemView.setOnClickListener {
                        listener.itemClick(297762)
                    }
                }
                10402 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(446893)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_music)
                        .into(imageGenres)
                }
                9648 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(703771)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_mystery)
                        .into(imageGenres)
                }
                10749 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(613504)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_romatic)
                        .into(imageGenres)
                }
                878 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(507441)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_sciencefiction)
                        .into(imageGenres)
                }
                10770 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(107706)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_tvmovie)
                        .into(imageGenres)
                }
                53 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(539885)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_war).into(imageGenres)
                }
                37 -> {
                    itemView.setOnClickListener {
                        listener.itemClick(55621)

                    }
                    Glide.with(itemView.context).load(R.drawable.image_western).into(imageGenres)
                }
            }


        }

    }
}