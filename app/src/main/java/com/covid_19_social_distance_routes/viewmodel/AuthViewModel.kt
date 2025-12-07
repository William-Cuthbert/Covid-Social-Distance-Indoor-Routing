package com.covid_19_social_distance_routes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var isAdmin by mutableStateOf(false)
        private set
    fun loginAsUser() { isAdmin = false }
    fun loginAsAdmin() { isAdmin = true }
}