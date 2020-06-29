package com.example.mvvmarchitectureappmovie.ui.detailmovie

import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmarchitectureappmovie.R
import com.example.mvvmarchitectureappmovie.data.model.ResultReviews
import com.example.mvvmarchitectureappmovie.data.model.Review
import kotlinx.android.synthetic.main.item_recycleview_reviews.view.*

class ReviewMovieAdapter() : RecyclerView.Adapter<ReviewMovieAdapter.ViewHolder>() {
    private var list: List<ResultReviews> = arrayListOf()

    fun setList(listReview: List<ResultReviews>) {
        list = listReview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycleview_reviews, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val tvAuthor = v.tvAuthorReview
        private val tvId = v.tvIdReview
        private val tvContent = v.tvContentReview
        private val tvUrl = v.tvUrl
        fun onBind(position: Int) {
            val review = list[position]
            tvAuthor.text = review.author.toUpperCase()
            tvContent.run {
                text = review.content
                addShowMoreText("More")
                addShowLessText("Less")
                setShowMoreColor(Color.GRAY)
                setShowLessTextColor(Color.GRAY)
                setShowingLine(3)
            }
            tvId.text = review.id
            tvUrl.apply {
                text = review.url
            }
        }
    }

}