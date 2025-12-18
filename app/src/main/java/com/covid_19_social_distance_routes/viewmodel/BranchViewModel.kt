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

                    val sorted = branches.sortedBy {
                        distanceBetween(
                            userLat,
                            userLon,
                            it.latitude,
                            it.longitude
                        )
                    }

                    _uiState.value = BranchUiState.Success(sorted)
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

    private fun distanceBetween(
        lat1: Double,
        lon1: Double,
        lat2: Double,
        lon2: Double
    ): Float {
        val results = FloatArray(1)
        Location.distanceBetween(
            lat1, lon1, lat2, lon2, results
        )
        return results[0]
    }
}
