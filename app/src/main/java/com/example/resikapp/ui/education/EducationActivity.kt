package com.example.resikapp.ui.education

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resikapp.R
import com.example.resikapp.data.retrofit.ApiConfig
import com.example.resikapp.data.response.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EducationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var educationAdapter: EducationAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        educationAdapter = EducationAdapter()
        recyclerView.adapter = educationAdapter

        fetchEducationData()
    }

    private fun fetchEducationData() {
        showLoading(true)
        val client = ApiConfig.getApiService().getUsers()

        client.enqueue(object : Callback<List<UserItem>> {

            override fun onResponse(call: Call<List<UserItem>>, response: Response<List<UserItem>>) {
                showLoading(false)
                Log.d("112233", "User avatar URL: ${response}")

                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        Log.d("112233", "User avatar URL: ${users}")
                        educationAdapter.setUsers(users)
                        recyclerView.visibility = View.VISIBLE
                    }
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                showLoading(false)
                // Handle failure
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }
}
