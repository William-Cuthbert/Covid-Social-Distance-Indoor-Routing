package com.covid_19_social_distance_routes.viewmodel

import androidx.lifecycle.ViewModel

import com.covid_19_social_distance_routes.model.AuthType

class AuthViewModel : ViewModel() {

    private val validUsers = mapOf(
        "admin" to Pair("1234", true),
        "user" to Pair("1234", false)
    )

    fun login(username: String, password: String): AuthType? {
        val user = validUsers[username.lowercase()] ?: return null
        return if (password == user.first) AuthType(isAdmin = user.second) else null
    }

    fun loginAsUser() = AuthType(false)
    fun loginAsAdmin() = AuthType(true)
}