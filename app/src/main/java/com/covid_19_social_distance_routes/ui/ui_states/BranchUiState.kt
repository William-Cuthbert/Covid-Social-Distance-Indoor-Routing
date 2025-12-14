package com.covid_19_social_distance_routes.ui.ui_states

import com.covid_19_social_distance_routes.model.Branch

sealed class BranchUiState {
    object Loading : BranchUiState()
    data class Success(val branches: List<Branch>) : BranchUiState()
    data class Error(val message: String) : BranchUiState()
}