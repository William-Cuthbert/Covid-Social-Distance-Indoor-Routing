package com.covid_19_social_distance_routes.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.covid_19_social_distance_routes.utility.validateEmail
import com.covid_19_social_distance_routes.utility.validatePassword

import com.covid_19_social_distance_routes.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Column(Modifier.fillMaxSize().statusBarsPadding().padding(24.dp)) {
        Text("Login", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(24.dp))

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
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        imageVector = if (showPassword) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        if (passwordError != null) {
            Text(passwordError!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                val emailValidated = validateEmail(email)
                val passwordValidated = validatePassword(password)

                if (emailValidated == null && passwordValidated == null) {
                    authViewModel.login(email, password) {
                        if (authViewModel.userRole == "admin") {
                            navController.navigate("admin_dashboard")
                        } else {
                            navController.navigate("branches")
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login")
        }

        TextButton(
            onClick = { navController.navigate("forgot_password") },
            modifier = Modifier.fillMaxWidth()) {
            Text("Forgot Password?")
        }

        TextButton(
            onClick = { navController.navigate("signup") },
            modifier = Modifier.fillMaxWidth()) {
            Text("Don't have an account? Sign Up")
        }

        authViewModel.error?.let {
            Spacer(Modifier.height(12.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}