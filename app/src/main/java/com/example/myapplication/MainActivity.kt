package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter
    private lateinit var tempText: TextView
    private lateinit var humidityText: TextView
    private lateinit var btnFetch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- UI references ---
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        tempText = findViewById(R.id.textTemperature)
        humidityText = findViewById(R.id.textHumidity)
        btnFetch = findViewById(R.id.btnFetch)

        // --- Load user info from SharedPreferences ---
        val prefs = getSharedPreferences("TempDataPrefs", MODE_PRIVATE)
        val firstName = prefs.getString("FIRST_NAME", "")
        val email = prefs.getString("EMAIL", "")

        tvWelcome.text = "Welcome, $firstName!\n($email)"

        // --- Init Presenter for Sensor Data ---
        presenter = MainPresenter(this)
        presenter.loadSensorData() // initial load

        // --- Fetch Sensor Data manually ---
        btnFetch.setOnClickListener {
            presenter.loadSensorData()
        }

        // --- Button Handlers ---
        btnProfile.setOnClickListener {
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

    // --- MVP Callbacks ---
    override fun showSensorData(data: SensorData) {
        tempText.text = "Temperature: ${"%.1f".format(data.temperature)} °C"
    }

    override fun showError(message: String) {
        tempText.text = "Error"
        humidityText.text = message
    }
}
