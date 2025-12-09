package com.covid_19_social_distance_routes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import com.covid_19_social_distance_routes.model.AuthType
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)
    var userRole by mutableStateOf<String?>(null)

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        isLoading = true
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { result ->
            isLoading = false
            if (result.isSuccessful) {
                loadUserRole(onSuccess)
            } else {
                error = result.exception?.message
            }
        }
    }

    fun signUp(email: String, password: String, name: String, onComplete: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid ?: return@addOnSuccessListener
                val userData = mapOf(
                    "email" to email,
                    "name" to name,
                    "role" to "user" // normal user by default
                )
                db.collection("users").document(uid).set(userData)
                    .addOnSuccessListener { onComplete(true) }
                    .addOnFailureListener { onComplete(false) }
            }
            .addOnFailureListener { onComplete(false) }
    }

    private fun loadUserRole(onSuccess: () -> Unit) {
        val uid = auth.currentUser?.uid ?: return
        db.collection("users").document(uid)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                userRole = documentSnapshot.getString("role")
                onSuccess()
            }
            .addOnFailureListener { e ->
                error = e.message
            }
    }

    fun loginAsUser() = AuthType(false)
    fun loginAsAdmin() = AuthType(true)
}