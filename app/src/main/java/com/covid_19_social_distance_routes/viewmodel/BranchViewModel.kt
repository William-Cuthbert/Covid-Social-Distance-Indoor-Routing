package com.covid_19_social_distance_routes.viewmodel;

import android.content.Context
import androidx.compose.runtime.State
import android.location.Location
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.covid_19_social_distance_routes.repository.BranchRepository
import com.covid_19_social_distance_routes.ui.ui_states.BranchUiState
import com.google.android.gms.location.LocationServices

class BranchViewModel(
    private val repository: BranchRepository
) : ViewModel() {

    private val _uiState = mutableStateOf<BranchUiState>(BranchUiState.Loading)
    val uiState: State<BranchUiState> = _uiState

    fun onLocationPermissionGranted(context: Context) {
        loadBranchesNearUser(context)
    }

    private fun loadBranchesNearUser(context: Context) {
        _uiState.value = BranchUiState.Loading

        try {
            val fusedClient = LocationServices
                .getFusedLocationProviderClient(context)

            fusedClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location == null) {
                        _uiState.value = BranchUiState.Error("Unable to get location")
                        return@addOnSuccessListener
                    }

                    val userLat = location.latitude
                    val userLon = location.longitude

                    val branches = repository.getBranches()

                    val updated = branches.map { branch ->
                        val results = FloatArray(1)
                        Location.distanceBetween(
                            userLat,
                            userLon,
                            branch.latitude,
                            branch.longitude,
                            results
                        )
                        branch.copy(distanceMeters = results[0])
                    }.sortedBy { it.distanceMeters }

                    _uiState.value = BranchUiState.Success(updated)
                }
                .addOnFailureListener {
                    _uiState.value =
                        BranchUiState.Error("Location fetch failed")
                }

        } catch (e: Exception) {
            _uiState.value =
                BranchUiState.Error("Unexpected error")
        }
    }
}
