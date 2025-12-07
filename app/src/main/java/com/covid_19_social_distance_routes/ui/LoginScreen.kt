package com.covid_19_social_distance_routes.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.covid_19_social_distance_routes.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Login", style = MaterialTheme.typography.headlineLarge)
        Spacer(Modifier.height(32.dp))
        Button(onClick = {
            authViewModel.loginAsUser()
            navController.navigate("branches")
        }) {
            Text("Enter as User")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            authViewModel.loginAsAdmin()
            navController.navigate("admin_dashboard")
        }) {
            Text("Enter as Admin")
        }
    }
}