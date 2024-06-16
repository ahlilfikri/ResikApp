package com.example.resikapp.ui.education

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        educationAdapter = EducationAdapter()
        recyclerView.adapter = educationAdapter

        fetchEducationData()
    }

    private fun fetchEducationData() {
        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(call: Call<List<UserItem>>, response: Response<List<UserItem>>) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        educationAdapter.setUsers(users)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
