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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.covid_19_social_distance_routes.viewmodel.AuthViewModel

@Composable
fun ForgotPasswordScreen(navController: NavController, viewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    val status by viewModel.status.collectAsState()

    Column(modifier = Modifier.statusBarsPadding().fillMaxSize().padding(16.dp)) {
        Text("Forgot Password", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Email")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.sendPasswordReset(email)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Send Reset Email")
        }
        Spacer(modifier = Modifier.height(16.dp))

        status?.let { Text(it) }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Login")
        }
    }
}