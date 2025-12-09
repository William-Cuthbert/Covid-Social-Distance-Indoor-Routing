package com.covid_19_social_distance_routes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

@Composable
fun BranchListScreen(
    navController: NavController,
    viewModel: BranchViewModel)
{
    val branches = viewModel.branches

    Column(Modifier.fillMaxSize().statusBarsPadding().padding(16.dp)) {
        Text("Select a location", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        if (branches.isEmpty()) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Text("Not available at the moment")
                }
            }
        } else {
            branches.forEach { branch ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable {
                        navController.navigate("floors/${branch.id}")
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(branch.name + " - " + branch.location)
                    }
                }
            }
        }
    }
}