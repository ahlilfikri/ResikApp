package com.example.resikapp.ui.pickupWorker

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.resikapp.R
import com.example.resikapp.data.response.ApiResponsePesananOnComing
import com.example.resikapp.data.response.HistoryPesananItem
import com.example.resikapp.data.retrofit.ApiConfig
import com.example.resikapp.ui.pickupWorker.detail.DetailFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class PickUpWorkerActivity : AppCompatActivity(), OnComingAdapter.OnItemClickListener {
    private lateinit var onComingRecyclerView: RecyclerView
    private lateinit var onComingAdapter: OnComingAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var nullTextView: TextView
    private lateinit var instructionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_up_worker)

        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.errorTextView)
        nullTextView = findViewById(R.id.nullTextView)
        instructionTextView = findViewById(R.id.instructionTextView)
        onComingRecyclerView = findViewById(R.id.onComingRecyclerView)

        onComingRecyclerView.layoutManager = LinearLayoutManager(this)
        onComingAdapter = OnComingAdapter(this)
        onComingRecyclerView.adapter = onComingAdapter

        fetchOnComingData()
    }

    override fun onItemClick(pesanan: HistoryPesananItem) {
        val detailFragment = DetailFragment.newInstance(pesanan, "param2")
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
        instructionTextView.visibility = View.GONE
    }

    private fun fetchOnComingData() {
        showLoading2(true)
        val client = ApiConfig.getApiService(this).getDaftarPesanan()
        client.enqueue(object : Callback<ApiResponsePesananOnComing> {
            override fun onResponse(call: Call<ApiResponsePesananOnComing>, response: Response<ApiResponsePesananOnComing>) {
                showLoading2(false)
                if (response.isSuccessful) {
                    val pesanans = response.body()
                    if (pesanans != null && pesanans.data.listRequest != null) {
                        onComingAdapter.setPesanans(pesanans.data.listRequest)
                        showError2(false)
                        instructionTextView.visibility = View.VISIBLE
                    } else {
                        showError2(true)
                    }
                } else {
                    showError2(true)
                }
            }

            override fun onFailure(call: Call<ApiResponsePesananOnComing>, t: Throwable) {
                showLoading2(false)
                showError2(true)
            }
        })
    }

    private fun showLoading2(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            onComingRecyclerView.visibility = View.GONE
            errorTextView.visibility = View.GONE
            nullTextView.visibility = View.GONE
            instructionTextView.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            onComingRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun showError2(show: Boolean) {
        if (show) {
            errorTextView.visibility = View.VISIBLE
            onComingRecyclerView.visibility = View.GONE
        } else {
            errorTextView.visibility = View.GONE
            onComingRecyclerView.visibility = View.VISIBLE
        }
    }
}
