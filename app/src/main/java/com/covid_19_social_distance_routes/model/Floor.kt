package com.covid_19_social_distance_routes.model

data class Floor(
    val id: String,
    val level: Int,
    val nodes: List<Node>
)
