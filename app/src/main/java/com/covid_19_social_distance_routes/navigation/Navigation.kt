package com.covid_19_social_distance_routes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.covid_19_social_distance_routes.repository.BranchRepository

import com.covid_19_social_distance_routes.ui.user.branch.BranchListScreen
import com.covid_19_social_distance_routes.ui.user.FloorListScreen
import com.covid_19_social_distance_routes.ui.user.FloorPlanScreen
import com.covid_19_social_distance_routes.ui.login.ForgotPasswordScreen
import com.covid_19_social_distance_routes.ui.login.LoginScreen
import com.covid_19_social_distance_routes.ui.login.SignUpScreen
import com.covid_19_social_distance_routes.ui.login.UpdatePasswordScreen
import com.covid_19_social_distance_routes.ui.admin.AdminDashboardScreen

import com.covid_19_social_distance_routes.viewmodel.AuthViewModel
import com.covid_19_social_distance_routes.viewmodel.BranchViewModel
import com.covid_19_social_distance_routes.viewmodel.factory.BranchViewModelFactory

@Composable
fun AppNavHost(navController: NavHostController) {

    val authViewModel: AuthViewModel = viewModel()
    val branchViewModel: BranchViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("forgot_password") { ForgotPasswordScreen(navController, authViewModel) }
        composable("update_password") { UpdatePasswordScreen(navController, authViewModel) }
        composable("signup") { SignUpScreen(navController, authViewModel) }
        composable("admin_dashboard") { AdminDashboardScreen(navController, viewModel = branchViewModel) }
        composable("branches") {
            val repository = remember { BranchRepository() }
            val branchViewModel: BranchViewModel = viewModel(factory = BranchViewModelFactory(repository))
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


