package com.covid_19_social_distance_routes.mapper

import com.google.firebase.auth.FirebaseAuthException

object FirebaseAuthErrorMapper {

    fun map(exception: Exception?): String {
        val errorCode = (exception as? FirebaseAuthException)?.errorCode

        return when (errorCode) {
            "ERROR_INVALID_EMAIL" ->
                "The email address is badly formatted."
            "ERROR_WRONG_PASSWORD" ->
                "Incorrect password. Please try again."
            "ERROR_USER_NOT_FOUND" ->
                "No account found with this email."
            "ERROR_USER_DISABLED" ->
                "This account has been disabled."
            "ERROR_TOO_MANY_REQUESTS" ->
                "Too many attempts. Please wait and try again."
            "ERROR_OPERATION_NOT_ALLOWED" ->
                "Password login is disabled for this project."
            "ERROR_INVALID_CREDENTIAL" ->
                "The supplied credentials are invalid or expired."
            else ->
                exception?.localizedMessage ?: "Authentication failed. Please try again."
        }
    }
}