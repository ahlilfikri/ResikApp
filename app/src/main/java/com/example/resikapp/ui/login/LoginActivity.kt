package com.example.resikapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.resikapp.R
import com.example.resikapp.data.response.LoginRequest
import com.example.resikapp.data.response.LoginResponse
import com.example.resikapp.data.retrofit.ApiConfig
import com.example.resikapp.databinding.ActivityLoginBinding
import com.example.resikapp.ui.homeui.HomeActivity
import com.example.resikapp.ui.register.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvDaftar.setOnClickListener { startActivity(Intent(this@LoginActivity, RegisterActivity::class.java)) }
    }

    private fun login(username: String, password: String) {
        val client = ApiConfig.getApiService().login(LoginRequest(username, password))
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.status == "success") {
                        val token = loginResponse.data?.token
                        // Simpan token ke SharedPreferences atau sesi lainnya
                        // Lanjutkan ke HomeActivity
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finishAffinity()
                    } else {
                        Toast.makeText(this@LoginActivity, loginResponse?.message ?: "Login failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
