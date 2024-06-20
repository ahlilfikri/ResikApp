package com.example.resikapp.ui.pickUpWorker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resikapp.R
import com.example.resikapp.data.response.UserItem
import com.example.resikapp.data.retrofit.ApiConfig
import com.example.resikapp.ui.pickupUser.HistoryAdapter
import com.example.resikapp.ui.pickupUser.OnComingAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PickUpWorkerActivity : AppCompatActivity() {
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var onComingRecyclerView: RecyclerView
    private lateinit var onComingAdapter: OnComingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_up_worker)

        historyRecyclerView = findViewById(R.id.historyRecyclerView)
        onComingRecyclerView = findViewById(R.id.onComingRecyclerView)

        historyRecyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter()
        historyRecyclerView.adapter = historyAdapter

        onComingRecyclerView.layoutManager = LinearLayoutManager(this)
        onComingAdapter = OnComingAdapter()
        onComingRecyclerView.adapter = onComingAdapter


        fetchHistoryData()
        fetchOnComingData()

    }

    private fun fetchHistoryData() {
        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        historyAdapter.setUsers(users)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }

    private fun fetchOnComingData() {
        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(
                call: Call<List<UserItem>>,
                response: Response<List<UserItem>>
            ) {
                if (response.isSuccessful) {
                    val users = response.body()
                    if (users != null) {
                        onComingAdapter.setUsers(users)
                    }
                }
            }

            override fun onFailure(call: Call<List<UserItem>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}