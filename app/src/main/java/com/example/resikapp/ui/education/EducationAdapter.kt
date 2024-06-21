package com.example.resikapp.ui.education

import com.example.resikapp.ui.deatilEducation.DetailEducationActivity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.resikapp.R
import com.example.resikapp.data.response.ArticleItem
import com.example.resikapp.data.response.UserItem

class EducationAdapter : RecyclerView.Adapter<EducationAdapter.ViewHolder>() {
    private val articles = mutableListOf<ArticleItem>()

    fun setArticles(articles: List<ArticleItem>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_education, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailEducationActivity::class.java)
            intent.putExtra("id", article.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = articles.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(article: ArticleItem) {
            nameTextView.text = article.penulis

            descriptionTextView.text = article.judul

            Glide.with(itemView.context)
                .load(article.image)
                .into(avatarImageView)
        }
    }

}