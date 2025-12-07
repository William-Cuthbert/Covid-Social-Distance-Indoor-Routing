package com.covid_19_social_distance_routes

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.navigation.compose.rememberNavController

import com.covid_19_social_distance_routes.navigation.AppNavHost

import com.covid_19_social_distance_routes.ui.theme.Covid19SocialDistanceRoutesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Covid19SocialDistanceRoutesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}
