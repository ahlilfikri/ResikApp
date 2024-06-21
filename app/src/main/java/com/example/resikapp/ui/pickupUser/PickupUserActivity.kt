package com.example.resikapp.ui.pickupUser

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.auth0.android.jwt.JWT
import com.example.resikapp.R
import com.example.resikapp.data.response.ApiResponsePesanan
import com.example.resikapp.data.response.CreatePesananRequest
import com.example.resikapp.data.response.CreatePesananResponse
import com.example.resikapp.data.retrofit.ApiConfig
import com.example.resikapp.helper.sharedpreferencetoken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PickupUserActivity : AppCompatActivity() {
    private lateinit var sharedpreferencetoken: sharedpreferencetoken

    private lateinit var addressEditText: EditText
    private lateinit var addPickupButton: Button
    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pickup_user)

        addressEditText = findViewById(R.id.addressEditText)
        addPickupButton = findViewById(R.id.addPickupButton)
        historyRecyclerView = findViewById(R.id.historyRecyclerView)
        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.errorTextView)

        historyRecyclerView.layoutManager = LinearLayoutManager(this)
        historyAdapter = HistoryAdapter()
        historyRecyclerView.adapter = historyAdapter

        fetchPesananData()
        sharedpreferencetoken = sharedpreferencetoken(this)

        addPickupButton.setOnClickListener {
            val alamat = addressEditText.text.toString()
            Log.d("pesananhistory", "onCreate:${alamat} ")
            if (alamat.isNotEmpty()) {
                createPesanan(alamat)
            } else {
                Toast.makeText(this, "Alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createPesanan(alamat: String) {
        showLoading(true)
        val client = ApiConfig.getApiService(this).createPesanan(CreatePesananRequest(alamat))
        client.enqueue(object : Callback<CreatePesananResponse> {
            override fun onResponse(call: Call<CreatePesananResponse>, response: Response<CreatePesananResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val createPesananResponse = response.body()
                    if (createPesananResponse != null && createPesananResponse.status == "success") {
                        Toast.makeText(this@PickupUserActivity, "Pesanan berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        fetchPesananData() // Refresh pesanan data
                    } else {
                        Toast.makeText(this@PickupUserActivity, "Gagal menambahkan pesanan", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PickupUserActivity, "Gagal menambahkan pesanan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CreatePesananResponse>, t: Throwable) {
                Log.d("createPesanan", "onFailure: ${t}")
                showLoading(false)
                Toast.makeText(this@PickupUserActivity, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchPesananData() {
        showLoading(true)
        val client = ApiConfig.getApiService(this).getPesanan()
        client.enqueue(object : Callback<ApiResponsePesanan> {
            override fun onResponse(call: Call<ApiResponsePesanan>, response: Response<ApiResponsePesanan>) {
                showLoading(false)
                if (response.isSuccessful) {
                    val pesanans = response.body()
                    if (pesanans != null) {
                        val token = sharedpreferencetoken.getToken()

                        if (token != null) {
                            val jwt = JWT(token)
                            username = jwt.getClaim("username").asString()
                        }

                        historyAdapter.setPesanans(pesanans.data.pesanan, username)
                        showError(false)
                    } else {
                        showError(true)
                    }
                } else {
                    showError(true)
                }
            }

            override fun onFailure(call: Call<ApiResponsePesanan>, t: Throwable) {
                Log.d("pesananhistory", "onFailure: ${t}")
                showLoading(false)
                showError(true)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            historyRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            historyRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            errorTextView.visibility = View.VISIBLE
            historyRecyclerView.visibility = View.GONE
        } else {
            errorTextView.visibility = View.GONE
            historyRecyclerView.visibility = View.VISIBLE
        }
    }
}
