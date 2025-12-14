package com.covid_19_social_distance_routes.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.covid_19_social_distance_routes.repository.BranchRepository
import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

class BranchViewModelFactory(private val repository: BranchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BranchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BranchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}