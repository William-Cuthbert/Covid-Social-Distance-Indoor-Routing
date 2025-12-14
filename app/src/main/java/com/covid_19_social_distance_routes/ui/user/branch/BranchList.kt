package com.covid_19_social_distance_routes.ui.user.branch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.covid_19_social_distance_routes.model.Branch

@Composable
fun BranchList(branches: List<Branch>, onBranchClick: (String) -> Unit) {
    Column {
        branches.forEach { branch ->
            BranchCard(branch = branch, onClick = onBranchClick)
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}