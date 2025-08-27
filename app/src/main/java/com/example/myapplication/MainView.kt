package com.example.myapplication

import com.example.myapplication.SensorData

interface MainView {
    fun showSensorData(data: SensorData)
    fun showError(message: String)
}