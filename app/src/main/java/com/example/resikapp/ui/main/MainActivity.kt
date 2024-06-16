package com.example.resikapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.resikapp.R
import com.example.resikapp.ui.education.EducationActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val educationLayout: LinearLayout = findViewById(R.id.educationLayout)
        educationLayout.setOnClickListener {
            startActivity(Intent(this, EducationActivity::class.java))
        }
    }
}
