package com.covid_19_social_distance_routes.ui.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

@Composable
fun AdminDashboardScreen(
    navController: NavController,
    viewModel: BranchViewModel
) {
    val branches = viewModel.branches
    var selectedBranchId by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp)) {
        Text("Admin Dashboard", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        if (selectedBranchId == null) {
            Text("Select a Branch: ", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            branches.forEach { branch ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable {
                        selectedBranchId = branch.id
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(branch.name, style = MaterialTheme.typography.titleLarge)
                        Text("Location: ${branch.location}")
                        Text("Floors: ${branch.floors.size}")
                    }
                }
            }
        } else {
            val branch = branches.find { it.id == selectedBranchId }

            Button(onClick = {selectedBranchId = null }) {
                Text("<- Back to Branches")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("${branch?.name} - Floors", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            branch?.floors?.forEach { floor ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable {
                        navController.navigate("floor_plan/${branch.id}/${floor.id}")
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("Floor: ${floor.level}", style = MaterialTheme.typography.titleLarge)
                        Text("Nodes: ${floor.nodes.size}")
                    }
                }

            }
        }
    }
}