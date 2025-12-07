package com.covid_19_social_distance_routes.model

class NavGraph {
    private val nodes = mutableListOf<Node>()

    fun addNode(node: Node) {
        nodes.add(node)
    }

    fun getNode(id: String): Node? {
        return nodes.find { it.id == id }
    }

    fun getAllNodes(): List<Node> = nodes.toList()
}