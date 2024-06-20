package com.example.resikapp.ui.deatilEducation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.resikapp.R
import com.example.resikapp.data.response.ApiResponseDetailEducation
import com.example.resikapp.data.response.ArticleItem
import com.example.resikapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailEducationActivity : AppCompatActivity() {

    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var writerTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_education)

        avatarImageView = findViewById(R.id.avatarImageView)
        nameTextView = findViewById(R.id.nameTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        writerTextView = findViewById(R.id.writerTextView)
        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.errorTextView)

        val id = intent.getStringExtra("id")
        if (id != null) {
            fetchArticleDetails(id)
        }
    }

    private fun fetchArticleDetails(id: String) {
        showLoading(true)
        val client = ApiConfig.getApiService(this).getDetailArticle(id)
        client.enqueue(object : Callback<ApiResponseDetailEducation>  {
            override fun onResponse(call: Call<ApiResponseDetailEducation>, response: Response<ApiResponseDetailEducation>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val article = response.body()
                    if (article != null) {
                        bindArticleDetails(article.data.artikel)
                        showError(false)
                    } else {
                        Log.d("detailarticle", "onFailure: ${article}")
                        showError(true)
                    }
                } else {
                    Log.d("detailarticle", "onFailure: ${response }")
                    showError(true)
                }
            }

            override fun onFailure(call: Call<ApiResponseDetailEducation>, t: Throwable) {
                Log.d("detailarticle", "onFailure: ${t}")
                showLoading(false)
                showError(true)
            }
        })
    }

    private fun bindArticleDetails(article: ArticleItem ) {
        nameTextView.text = article.judul
        writerTextView.text = article.penulis
        descriptionTextView.text = article.isi
//        Glide.with(this)
//            .load(user.avatarUrl)
//            .into(avatarImageView)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            avatarImageView.visibility = View.GONE
            nameTextView.visibility = View.GONE
            descriptionTextView.visibility = View.GONE
            errorTextView.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            avatarImageView.visibility = View.VISIBLE
            nameTextView.visibility = View.VISIBLE
            descriptionTextView.visibility = View.VISIBLE
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            errorTextView.visibility = View.VISIBLE
            avatarImageView.visibility = View.GONE
            nameTextView.visibility = View.GONE
            descriptionTextView.visibility = View.GONE
        } else {
            errorTextView.visibility = View.GONE
        }
    }
}
