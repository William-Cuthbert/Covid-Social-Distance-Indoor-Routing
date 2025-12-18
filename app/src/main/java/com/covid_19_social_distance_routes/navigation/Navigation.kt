package com.covid_19_social_distance_routes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.covid_19_social_distance_routes.repository.BranchRepository

import com.covid_19_social_distance_routes.ui.user.branch.BranchListScreen
import com.covid_19_social_distance_routes.ui.login.ForgotPasswordScreen
import com.covid_19_social_distance_routes.ui.login.LoginScreen
import com.covid_19_social_distance_routes.ui.login.SignUpScreen
import com.covid_19_social_distance_routes.ui.login.UpdatePasswordScreen

import com.covid_19_social_distance_routes.viewmodel.AuthViewModel
import com.covid_19_social_distance_routes.viewmodel.BranchViewModel
import com.covid_19_social_distance_routes.viewmodel.factory.BranchViewModelFactory

@Composable
fun AppNavHost(navController: NavHostController) {

    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController, authViewModel)
        }

        composable("forgot_password") {
            ForgotPasswordScreen(navController, authViewModel)
        }

        composable("update_password") {
            UpdatePasswordScreen(navController, authViewModel)
        }

        composable("signup") {
            SignUpScreen(navController, authViewModel)
        }

        composable("branches") {
            val context = LocalContext.current
            val repository = remember { BranchRepository() }
            val branchViewModel: BranchViewModel = viewModel(
                factory = BranchViewModelFactory(repository)
            )
            BranchListScreen(
                navController = navController,
                viewModel = branchViewModel,
                context = context
            )
        }
    }
}


