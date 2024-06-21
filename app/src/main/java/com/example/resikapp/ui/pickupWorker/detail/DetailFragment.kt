package com.example.resikapp.ui.pickupWorker.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.resikapp.R
import com.example.resikapp.data.response.CreatePesananResponse
import com.example.resikapp.data.response.HistoryPesananItem
import com.example.resikapp.data.response.UpdatePesananRequest
import com.example.resikapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailFragment : Fragment() {
    private var param1: HistoryPesananItem? = null
    private var param2: String? = null

    private lateinit var usernameTextView: TextView
    private lateinit var alamatTextView: TextView
    private lateinit var beratEditText: EditText
    private lateinit var hargaEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameTextView = view.findViewById(R.id.usernameTextView)
        alamatTextView = view.findViewById(R.id.alamatTextView)
        beratEditText = view.findViewById(R.id.beratEditText)
        hargaEditText = view.findViewById(R.id.hargaEditText)
        saveButton = view.findViewById(R.id.saveButton)
        progressBar = view.findViewById(R.id.progressBar)

        param1?.let {
            usernameTextView.text = "Nama: ${it.user}"
            alamatTextView.text = "Alamat: ${it.alamat}"
            beratEditText.setText(it.berat)
            hargaEditText.setText(it.harga)
        }

        saveButton.setOnClickListener {
            val berat = beratEditText.text.toString()
            val harga = hargaEditText.text.toString()

            if (berat.isNotEmpty() && harga.isNotEmpty()) {
                savePesanan(berat, harga)
            } else {
                Toast.makeText(context, "Berat dan Harga tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun savePesanan(berat: String, harga: String) {
        progressBar.visibility = View.VISIBLE
        val updateRequest = UpdatePesananRequest(berat, harga)
        param1?.let { pesanan ->
            val client = ApiConfig.getApiService(requireContext()).updatePesanan(pesanan.id, updateRequest)
            client.enqueue(object : Callback<CreatePesananResponse> {
                override fun onResponse(call: Call<CreatePesananResponse>, response: Response<CreatePesananResponse>) {
                    Log.d("editpesanan", "onResponse: ${response}")
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful) {
                        Toast.makeText(context, "Pesanan berhasil diupdate", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Gagal mengupdate pesanan", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<CreatePesananResponse>, t: Throwable) {
                    Log.d("editpesanan", "onFailure: ${t}")
                    progressBar.visibility = View.GONE
                    Toast.makeText(context, "Terjadi kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: HistoryPesananItem, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
