package com.covid_19_social_distance_routes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun FloorListScreen(
    navController: NavController,
    branchId: String,
    viewModel: BranchViewModel
) {
    val branch = viewModel.branches.find { it.id == branchId }
    val floors = branch?.floors

    Column(Modifier.fillMaxSize().statusBarsPadding().padding(16.dp)) {
        Text("Floors in ${branch?.location}", style = MaterialTheme.typography.titleLarge)

        floors?.forEach { floor ->
            Card(
                Modifier.fillMaxWidth().padding(vertical = 6.dp).clickable {
                    navController.navigate("floor_plan/${branch.id}/${floor.id}")
                },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(getFloorLevel(floor.level))
                }
            }
        }
    }
}

fun getFloorLevel(num: Int): String {
    return if (num < 1) {
        "Ground Floor"
    } else {
        "Level $num"
    }
}