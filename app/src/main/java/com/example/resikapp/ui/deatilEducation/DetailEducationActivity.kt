package com.example.resikapp.ui.deatilEducation

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.resikapp.R
import com.example.resikapp.data.retrofit.ApiConfig
import com.example.resikapp.data.response.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEducationActivity : AppCompatActivity() {

    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_education)

        avatarImageView = findViewById(R.id.avatarImageView)
        nameTextView = findViewById(R.id.nameTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)

        val username = intent.getStringExtra("username")
        if (username != null) {
            fetchUserDetails(username)
        }
    }

    private fun fetchUserDetails(username: String) {
        val client = ApiConfig.getApiService().detailUser(username)
        client.enqueue(object : Callback<UserItem> {
            override fun onResponse(call: Call<UserItem>, response: Response<UserItem>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        bindUserDetails(user)
                    }
                }
            }

            override fun onFailure(call: Call<UserItem>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun bindUserDetails(user: UserItem) {
        nameTextView.text = user.login
        descriptionTextView.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et est libero."
        Glide.with(this)
            .load(user.avatarUrl)
            .into(avatarImageView)
    }
}
