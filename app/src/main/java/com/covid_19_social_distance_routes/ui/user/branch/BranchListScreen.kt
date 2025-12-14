package com.covid_19_social_distance_routes.ui.user.branch

import androidx.compose.animation.Crossfade
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

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.covid_19_social_distance_routes.ui.ui_states.BranchUiState
import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

@Composable
fun BranchListScreen(navController: NavController, viewModel: BranchViewModel) {

    val state by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 16.dp)) {
        Text("Select a location", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))

        Crossfade(
            targetState = state,
            label = "BranchState"
        ) { currentState ->
            when (currentState) {

                is BranchUiState.Loading -> {
                    Text(
                        text = "Loading locations...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                is BranchUiState.Error -> {
                    Text(
                        text = currentState.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is BranchUiState.Success -> {
                    if (currentState.branches.isEmpty()) {
                        EmptyBranchState()
                    } else {
                        BranchList(
                            branches = currentState.branches,
                            onBranchClick = { id ->
                                navController.navigate("floors/${id}")
                            }
                        )
                    }
                }
            }
        }
    }
}