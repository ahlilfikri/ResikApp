package com.example.resikapp.ui.pickupWorker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.resikapp.R
import com.example.resikapp.data.response.HistoryPesananItem
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryAdapterWorker : RecyclerView.Adapter<HistoryAdapterWorker.ViewHolder>() {
    private val pesanans = mutableListOf<HistoryPesananItem>()

    fun setPesanans(pesanans: List<HistoryPesananItem>) {
        this.pesanans.clear()
        this.pesanans.addAll(pesanans)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapterWorker.ViewHolder, position: Int) {
        val pesanan = pesanans[position]
        holder.bind(pesanan)
    }

    override fun getItemCount(): Int = pesanans.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        private val adressTextView: TextView = itemView.findViewById(R.id.adressTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)

        fun bind(pesanan: HistoryPesananItem) {
            usernameTextView.text = "Nama: ${pesanan.user}"
            adressTextView.text = "Alamat: ${pesanan.alamat}"
            dateTextView.text = formatDate(pesanan.created)
        }

        private fun formatDate(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            return if (date != null) {
                outputFormat.format(date)
            } else {
                dateString
            }
        }
    }
}
