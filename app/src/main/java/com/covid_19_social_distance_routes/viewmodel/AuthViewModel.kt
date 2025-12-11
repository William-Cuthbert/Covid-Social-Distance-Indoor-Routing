package com.covid_19_social_distance_routes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.EmailAuthProvider

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val _status = MutableStateFlow<String?>(null)
    val status: StateFlow<String?> = _status

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
                    "role" to "user"
                )
                db.collection("users").document(uid).set(userData)
                    .addOnSuccessListener { onComplete(true) }
                    .addOnFailureListener { onComplete(false) }
            }
            .addOnFailureListener { onComplete(false) }
    }

    fun sendPasswordReset(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener { _status.value = "Reset email sent" }
            .addOnFailureListener { _status.value = it.localizedMessage }
    }

    fun updatePassword(currentPassword: String, newPassword: String) {
        val user = auth.currentUser
        val email = user?.email ?: return

        val credential = EmailAuthProvider.getCredential(email, currentPassword)
        user.reauthenticate(credential)
            .addOnSuccessListener {
                user.updatePassword(newPassword)
                    .addOnSuccessListener { _status.value = "Password updated successfully" }
                    .addOnFailureListener { _status.value = it.localizedMessage }
            }
            .addOnFailureListener {
                _status.value = it.localizedMessage
            }
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
}