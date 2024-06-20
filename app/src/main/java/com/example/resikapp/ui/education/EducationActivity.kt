package com.example.resikapp.ui.education

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resikapp.R
import com.example.resikapp.data.response.ApiResponseEducation
import com.example.resikapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var educationAdapter: EducationAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        errorTextView = findViewById(R.id.errorTextView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        educationAdapter = EducationAdapter()
        recyclerView.adapter = educationAdapter

        fetchEducationData()
    }

    private fun fetchEducationData() {
        showLoading(true)
        val client = ApiConfig.getApiService(this).getArticles()

        client.enqueue(object : Callback<ApiResponseEducation> {

            override fun onResponse(call: Call<ApiResponseEducation>, response: Response<ApiResponseEducation>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    if (apiResponse != null && apiResponse.data.listArtikel.isNotEmpty()) {
                        educationAdapter.setArticles(apiResponse.data.listArtikel)
                        showError(false)
                        recyclerView.visibility = View.VISIBLE
                    } else {
                        showError(true)
                    }
                } else {
                    showError(true)
                }
            }

            override fun onFailure(call: Call<ApiResponseEducation>, t: Throwable) {
                showLoading(false)
                showError(true)
            }
        })
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
            errorTextView.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            errorTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            errorTextView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}
