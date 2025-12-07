package com.covid_19_social_distance_routes.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.covid_19_social_distance_routes.enums.NodeType
import com.covid_19_social_distance_routes.model.NavGraph
import com.covid_19_social_distance_routes.model.Node

class FloorViewModel : ViewModel() {

    private val graph = NavGraph()
    val nodes = mutableStateListOf<Node>()
    val roomObjects = mutableStateListOf<Node>()

    init {
        addRoomObject(Node("entrance", 50, 50, NodeType.ENTRANCE))
        addRoomObject(Node("table1", 150, 150, NodeType.TABLE))
        addRoomObject(Node("table2", 350, 150, NodeType.TABLE))
        addRoomObject(Node("wc", 500, 300, NodeType.WC,))
        addRoomObject(Node("bar", 300, 400, NodeType.BAR))

        addNode(Node("A", 50, 100, NodeType.NAVIGATING))
        addNode(Node("B", 200, 150, NodeType.NAVIGATING))
        addNode(Node("C", 120, 300, NodeType.NAVIGATING))
    }

    fun addNode(node: Node) {
        graph.addNode(node)
        nodes.add(node)
    }

    fun addRoomObject(obj: Node) {
        roomObjects.add(obj)
    }

    fun getNodeById(id: String): Node? = graph.getNode(id)

    fun getAllNodes(): List<Node> = graph.getAllNodes()
}