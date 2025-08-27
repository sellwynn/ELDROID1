package com.example.myapplication

import kotlin.random.Random

import com.example.myapplication.SensorData
import com.example.myapplication.MainView

class MainPresenter(private val view: MainView) {

    fun loadSensorData() {
        // Simulate fetching data (e.g., from BLE device or mock data)
        val fakeData = SensorData(temperature = 65.5f)

        if (fakeData.temperature > 0) {
            view.showSensorData(fakeData)
        } else {
            view.showError("Failed to read sensor data")
        }
    }
}