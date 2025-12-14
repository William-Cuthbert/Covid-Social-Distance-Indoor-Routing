package com.covid_19_social_distance_routes.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel

import com.covid_19_social_distance_routes.repository.BranchRepository
import com.covid_19_social_distance_routes.ui.ui_states.BranchUiState

import java.lang.Exception

class BranchViewModel(
    private val branchRepository : BranchRepository
) : ViewModel() {
    private val _uiState =
        mutableStateOf<BranchUiState>(BranchUiState.Loading)
    val uiState: State<BranchUiState> = _uiState

    init {
        loadBranches()
    }

    fun loadBranches() {
        _uiState.value = BranchUiState.Loading
        try {
            val branches = branchRepository.getBranches()
            _uiState.value = BranchUiState.Success(branches)
        } catch (e: Exception) {
            _uiState.value = BranchUiState.Error(
                e.message ?: "Failed to load locations")
        }
    }
}