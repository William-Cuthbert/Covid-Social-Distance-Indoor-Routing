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
fun FloorListScreen(
    navController: NavController,
    branchId: String,
    viewModel: BranchViewModel
) {
    val branch = viewModel.branches.find { it.id == branchId }
    Column(Modifier.padding(16.dp)) {
        Text("Floor of ${branch?.name} in ${branch?.location}", style = MaterialTheme.typography.titleLarge)
        branch?.floors?.let { floors ->
            LazyColumn {
                items(floors) { floor ->
                    Text(
                        floor.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("floor_plan/${branch.id}/${floor.id}")
                            }
                            .padding(12.dp)
                    )
                }
            }
        }
    }
}