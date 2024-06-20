package com.example.resikapp.ui.pickupUser

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.resikapp.R
import com.example.resikapp.data.response.UserItem

class OnComingAdapter : RecyclerView.Adapter<OnComingAdapter.ViewHolder>() {
    private val users = mutableListOf<UserItem>()

    fun setUsers(users: List<UserItem>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_oncoming, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int = users.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usnFromTextView: TextView = itemView.findViewById(R.id.usnFromTextView)
        private val usnToTextView: TextView = itemView.findViewById(R.id.usnToTextView)

        fun bind(user: UserItem) {
            usnFromTextView.text = user.login
            usnToTextView.text = user.login
        }
    }
}
