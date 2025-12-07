package com.covid_19_social_distance_routes.navigation

import androidx.compose.runtime.Composable

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.covid_19_social_distance_routes.ui.BranchListScreen
import com.covid_19_social_distance_routes.ui.FloorListScreen
import com.covid_19_social_distance_routes.ui.FloorPlanScreen
import com.covid_19_social_distance_routes.ui.LoginScreen
import com.covid_19_social_distance_routes.viewmodel.AuthViewModel
import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    val authViewModel: AuthViewModel = viewModel()
    val branchViewModel: BranchViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController, authViewModel)
        }
        composable("admin_dashboard") {
            throw RuntimeException("Not implemented yet!")
        }
        composable("branches") {
            BranchListScreen(navController, viewModel = branchViewModel)
        }
        composable("floors/{branchId}") { backStackEntry ->
            val branchId = backStackEntry.arguments?.getString("branchId") ?: return@composable
            FloorListScreen(navController, branchId = branchId, viewModel = branchViewModel)
        }
        composable("floor_plan/{branchId}/{floorId}") { backStackEntry ->
            val branchId = backStackEntry.arguments?.getString("branchId") ?: return@composable
            val floorId = backStackEntry.arguments?.getString("floorId") ?: return@composable
            FloorPlanScreen(branchId = branchId, floorId = floorId, viewModel = branchViewModel)
        }
    }
}


