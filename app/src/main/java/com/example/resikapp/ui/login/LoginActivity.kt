package com.example.resikapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.resikapp.R
import com.example.resikapp.databinding.ActivityLoginBinding
import com.example.resikapp.ui.homeui.HomeActivity
import com.example.resikapp.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
            finishAffinity()
        }
        binding.tvDaftar.setOnClickListener { startActivity(Intent(this@LoginActivity,RegisterActivity::class.java)) }

    }
}