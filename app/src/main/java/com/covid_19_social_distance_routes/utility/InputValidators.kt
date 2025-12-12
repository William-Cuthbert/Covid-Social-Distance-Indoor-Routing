package com.covid_19_social_distance_routes.utility

fun validateEmail(email: String): String? {
    return when {
        email.isBlank() -> "Email cannot be empty"
        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Please enter a valid email format"
        else -> null
    }
}

fun validatePassword(password: String): String? {
    return when {
        password.isBlank() -> "Password cannot be empty"
        password.length < 6 -> "Password must be at least 6 characters"
        else -> null
    }
}