package com.covid_19_social_distance_routes.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController

import com.covid_19_social_distance_routes.ui.AnimatedOutlinedTextField
import com.covid_19_social_distance_routes.utility.validateEmail
import com.covid_19_social_distance_routes.utility.validatePassword
import com.covid_19_social_distance_routes.viewmodel.AuthViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Sign up to get started",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(Modifier.height(24.dp))

                AnimatedOutlinedTextField(
                    value = name,
                    label = "Full Name",
                    onValueChange = { name = it }
                )
                Spacer(Modifier.height(20.dp))

                AnimatedOutlinedTextField(
                    value = email,
                    label = "Email",
                    onValueChange = {
                        email = it
                        emailError = validateEmail(it)
                    },
                    isError = emailError != null,
                    errorMessage = emailError
                )
                Spacer(Modifier.height(20.dp))

                AnimatedOutlinedTextField(
                    value = password,
                    label = "Password",
                    onValueChange = {
                        password = it
                        passwordError = validatePassword(it)
                    },
                    isError = passwordError != null,
                    errorMessage = passwordError,
                    isPassword = true
                )
                Spacer(Modifier.height(28.dp))

                Button(
                    onClick = {
                        authViewModel.signUp(email, password, name) { success ->
                            if (success) {
                                navController.navigate("branches") {
                                    popUpTo("signup") { inclusive = true }
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Create Account")
                }
                Spacer(Modifier.height(12.dp))

                TextButton(
                    onClick = { navController.navigate("login") }
                ) {
                    Text("Back to Login")
                }
            }
        }
    }
}