package com.covid_19_social_distance_routes.ui.user.branch

import android.Manifest
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.covid_19_social_distance_routes.ui.AnimatedLoadingText

import com.covid_19_social_distance_routes.ui.ui_states.BranchUiState
import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

@Composable
fun BranchListScreen(
    navController: NavController,
    viewModel: BranchViewModel,
    context: Context
) {

    val state by viewModel.uiState

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                viewModel.onLocationPermissionGranted(context)
            }
        }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(20.dp)
    ) {

        Text(
            "Select a location",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))

        Crossfade(targetState = state, label = "BranchState") { currentState ->
            when (currentState) {

                is BranchUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        AnimatedLoadingText(Modifier,"Loading nearby locations")
                    }
                }

                is BranchUiState.Error -> {
                    Text(
                        currentState.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is BranchUiState.Success -> {
                    BranchList(
                        branches = currentState.branches,
                        onBranchClick = {
                            navController.navigate("floors/$it")
                        }
                    )
                }
            }
        }
    }
}
