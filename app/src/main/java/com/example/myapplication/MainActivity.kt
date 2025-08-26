package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tempData: TempData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempData = TempData(this)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        // show saved name/email
        val firstName = getSharedPreferences("TempDataPrefs", MODE_PRIVATE)
            .getString("FIRST_NAME", "")
        val email = getSharedPreferences("TempDataPrefs", MODE_PRIVATE)
            .getString("EMAIL", "")

        tvWelcome.text = "Welcome, $firstName!\n($email)"

        btnProfile.setOnClickListener {
            // temporary: just show profile data
            tvWelcome.text = "Profile:\nName: $firstName\nEmail: $email"
        }

        btnSettings.setOnClickListener {
            tvWelcome.text = "Settings screen (to be implemented)"
        }

        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
