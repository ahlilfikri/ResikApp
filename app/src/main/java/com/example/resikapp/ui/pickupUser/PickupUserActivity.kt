package com.example.resikapp.ui.pickupUser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resikapp.R
import com.example.resikapp.data.response.UserItem
import com.example.resikapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PickupUserActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var addPickupButton: Button
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pickup_user)

        nameEditText = findViewById(R.id.nameEditText)
        addressEditText = findViewById(R.id.addressEditText)
        addPickupButton = findViewById(R.id.addPickupButton)
        historyRecyclerView = findViewById(R.id.historyRecyclerView)

        historyRecyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter()
        historyRecyclerView.adapter = historyAdapter

        fetchUserData()

        addPickupButton.setOnClickListener {
            // Handle add pickup logic
        }
    }

    private fun fetchUserData() {
        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object : Callback<List<UserItem>> {
            override fun onResponse(call: Call<List<UserItem>>, response: Response<List<UserItem>>) {
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
}
