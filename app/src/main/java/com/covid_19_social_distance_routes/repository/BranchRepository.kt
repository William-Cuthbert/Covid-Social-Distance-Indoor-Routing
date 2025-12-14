package com.covid_19_social_distance_routes.repository

import com.covid_19_social_distance_routes.enums.NodeType
import com.covid_19_social_distance_routes.model.Branch
import com.covid_19_social_distance_routes.model.Floor
import com.covid_19_social_distance_routes.model.Node

class BranchRepository {
    fun getBranches(): List<Branch> {
        return listOf(
            Branch(
                id = "1",
                name = "McDonald's",
                location = "London",
                floors = listOf(
                    Floor(
                        id = "LDN-GF",
                        level = 0,
                        nodes = listOf(
                            Node("A", 50, 100, NodeType.TABLE),
                            Node("B", 200, 150, NodeType.ENTRANCE)
                        )
                    ),
                    Floor(
                        id = "F1",
                        level = 1,
                        nodes = listOf(
                            Node("A", 50, 100, NodeType.TABLE),
                            Node("B", 200, 150, NodeType.ENTRANCE)
                        )
                    )
                ),
            ),
            Branch(
                id = "2",
                name = "McDonald's",
                location = "Glasgow",
                floors = listOf(
                    Floor(
                        id = "GLA-GF",
                        level = 0,
                        nodes = listOf(
                            Node("A", 150, 20, NodeType.TABLE),
                            Node("B", 20, 150, NodeType.ENTRANCE)
                        )
                    ),
                    Floor(
                        id = "GLA-F1",
                        level = 1,
                        nodes = listOf(
                            Node("A", 150, 20, NodeType.TABLE),
                            Node("B", 20, 150, NodeType.ENTRANCE)
                        )
                    )
                ),
            )
        )
    }
}