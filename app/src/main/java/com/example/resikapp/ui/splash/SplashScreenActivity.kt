package com.example.resikapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.resikapp.R
import com.example.resikapp.helper.sharedpreferencetoken
import com.example.resikapp.ui.homeui.HomeActivity
import com.example.resikapp.ui.login.LoginActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var sharedpreferencetoken: sharedpreferencetoken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        sharedpreferencetoken = sharedpreferencetoken(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val token = sharedpreferencetoken.getToken()
            if (token!=null){
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this,LoginActivity::class.java))
                finishAffinity()
            }
        }, 3000)
    }
}