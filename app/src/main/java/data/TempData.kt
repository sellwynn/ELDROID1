package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences

class TempData(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("TempDataPrefs", Context.MODE_PRIVATE)

    fun saveUser(firstName: String, lastName: String, email: String, password: String) {
        prefs.edit().apply {
            putString("FIRST_NAME", firstName)
            putString("LAST_NAME", lastName)
            putString("EMAIL", email)
            putString("PASSWORD", password)
            apply()
        }
    }

    fun isUserRegistered(): Boolean {
        return prefs.contains("EMAIL") && prefs.contains("PASSWORD")
    }

    fun validateLogin(email: String, password: String): Boolean {
        val savedEmail = prefs.getString("EMAIL", null)
        val savedPassword = prefs.getString("PASSWORD", null)
        return email == savedEmail && password == savedPassword
    }
}
