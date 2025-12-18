package com.covid_19_social_distance_routes.model

data class Branch(
    val id: String,
    val name: String,
    val location: String,
    val latitude: Double,
    val longitude: Double,
    val distanceMeters: Float? = null,
    val floors: List<Floor>
)
