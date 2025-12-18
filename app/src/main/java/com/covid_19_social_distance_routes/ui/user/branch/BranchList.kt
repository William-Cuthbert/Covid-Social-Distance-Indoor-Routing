package com.covid_19_social_distance_routes.ui.user.branch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed

import androidx.compose.runtime.Composable

import com.covid_19_social_distance_routes.model.Branch

@Composable
fun BranchList(
    branches: List<Branch>,
    onBranchClick: (String) -> Unit)
{
    LazyColumn {
        itemsIndexed(branches, key = { _, branch -> branch.id }) { index, branch ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(durationMillis = 400, delayMillis = index * 50)) +
                        slideInVertically(animationSpec = tween(durationMillis = 400, delayMillis = index * 50), initialOffsetY = { it / 2}),
            ) {
                BranchCard(branch = branch, onClick = { onBranchClick(branch.id) })
            }
        }
    }
}