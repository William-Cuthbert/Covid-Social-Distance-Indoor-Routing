package com.covid_19_social_distance_routes.repository

import com.covid_19_social_distance_routes.enums.NodeType
import com.covid_19_social_distance_routes.model.Branch
import com.covid_19_social_distance_routes.model.Floor
import com.covid_19_social_distance_routes.model.Node

class BranchRepository {
    fun getBranches(): List<Branch> {
        return listOf(
            Branch("1", "Central Hospital", "Downtown", 51.5074, -0.1278, listOf(
                Floor("LDN-GF", 0, nodes = listOf(Node("A", 50, 100, NodeType.TABLE), Node("B", 200, 150, NodeType.ENTRANCE))),
                Floor("F1", 1, listOf(Node("A", 50, 100, NodeType.TABLE), Node("B", 200, 150, NodeType.ENTRANCE))))),
            Branch("2", "West Clinic", "West End", 51.5155, -0.1420, listOf(
                Floor("GLA-GF", 0, listOf(Node("A", 150, 20, NodeType.TABLE), Node("B", 20, 150, NodeType.ENTRANCE))),
                Floor("GLA-F1", 1, listOf(Node("A", 150, 20, NodeType.TABLE), Node("B", 20, 150, NodeType.ENTRANCE))))),
            Branch("3", "East Medical", "East Side", 51.5200, -0.1000, listOf(
                Floor("GLA-GF", 0, listOf(Node("A", 150, 20, NodeType.TABLE), Node("B", 20, 150, NodeType.ENTRANCE))),
                Floor("GLA-F1", 1, listOf(Node("A", 150, 20, NodeType.TABLE), Node("B", 20, 150, NodeType.ENTRANCE)))))
        )
    }
}