package com.covid_19_social_distance_routes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.covid_19_social_distance_routes.enums.NodeType
import com.covid_19_social_distance_routes.model.Branch
import com.covid_19_social_distance_routes.model.Floor
import com.covid_19_social_distance_routes.model.Node

class BranchViewModel : ViewModel() {
    var branches by mutableStateOf(listOf<Branch>())
        private set
    init {
        branches = listOf(
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