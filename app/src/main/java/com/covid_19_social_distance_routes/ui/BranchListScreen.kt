package com.covid_19_social_distance_routes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

@Composable
fun BranchListScreen(navController: NavController, viewModel: BranchViewModel) {
    Column(Modifier.padding(16.dp)) {
        Text("Select Branch", style = MaterialTheme.typography.headlineLarge)
        LazyColumn {
            items(viewModel.branches) { branch ->
                Text(
                    branch.name + " - " + branch.location,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("floors/${branch.id}")
                        }
                        .padding(12.dp)
                )
            }
        }
    }
}