package com.example.resikapp.ui.pickupUser

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.resikapp.R
import com.example.resikapp.data.response.UserItem
import com.example.resikapp.ui.deatilEducation.DetailEducationActivity

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private val users = mutableListOf<UserItem>()
    fun setUsers(users: List<UserItem>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_education, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailEducationActivity::class.java)
            intent.putExtra("username", user.login)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

        fun bind(user: UserItem) {
            nameTextView.text = user.login
            descriptionTextView.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et est libero."
            Glide.with(itemView.context)
                .load(user.avatarUrl)
                .into(avatarImageView)
        }
    }
}
