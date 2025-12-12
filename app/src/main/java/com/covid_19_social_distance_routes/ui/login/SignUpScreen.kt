package com.covid_19_social_distance_routes.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.covid_19_social_distance_routes.utility.validateEmail
import com.covid_19_social_distance_routes.utility.validatePassword

import com.covid_19_social_distance_routes.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(navController: NavHostController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.statusBarsPadding().fillMaxSize().padding(24.dp)) {
        Text("Create New Account", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = validateEmail(it)
            },
            label = { Text("Email") },
            isError = emailError != null,
            modifier = Modifier.fillMaxWidth()
        )

        if (emailError != null) {
            Text(emailError!!, color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = validatePassword(it)
            },
            label = { Text("Password") },
            isError = passwordError != null,
            modifier = Modifier.fillMaxWidth()
        )

        if (passwordError != null) {
            Text(passwordError!!, color = MaterialTheme.colorScheme.error)
        }
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.signUp(email, password, name) { success ->
                    if (success) navController.navigate("branches") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create Account")
        }
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Login")
        }
    }
}
