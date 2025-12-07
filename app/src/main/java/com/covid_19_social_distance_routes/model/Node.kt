package com.covid_19_social_distance_routes.model

import com.covid_19_social_distance_routes.enums.NodeType

data class Node (
    val id: String,
    val x: Int,
    val y: Int,
    val type: NodeType
)