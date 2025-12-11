package com.covid_19_social_distance_routes.ui.user

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp

import com.covid_19_social_distance_routes.enums.NodeType
import com.covid_19_social_distance_routes.model.Node
import com.covid_19_social_distance_routes.viewmodel.BranchViewModel

@Composable
fun FloorPlanScreen(
    branchId: String,
    floorId: String,
    viewModel: BranchViewModel
) {
    val place = viewModel.branches.find { it.id == branchId }
    val floor = place?.floors?.find { it.id == floorId }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (floor == null) {
            Text("Floor not found", style = MaterialTheme.typography.titleLarge)
            return@Box
        }
        floor.nodes.forEach { node ->
            Box(
                modifier = Modifier
                    .offset(x = node.x.dp, y = node.y.dp)
                    .size(40.dp)
                    .background(
                        color = when (node.type) {
                            NodeType.TABLE -> Color.Blue
                            NodeType.ENTRANCE -> Color.Green
                            NodeType.WC -> Color.Red
                            NodeType.BAR -> Color.Yellow
                            NodeType.STAIRS -> Color.Magenta
                            NodeType.NAVIGATING -> Color.Cyan
                        },
                        shape = CircleShape
                    )
                    .clickable {
                        println("Clicked node ${node.id}")
                    }
            )
        }
    }
}

private fun DrawScope.drawWalls() {
    drawRect(
        color = Color.DarkGray,
        topLeft = Offset(0f, 0f),
        size = size.copy()
    )

    drawRect(
        color = Color.LightGray,
        topLeft = Offset(10f, 10f),
        size = size.copy(width = size.width - 20f, height = size.height - 20f)
    )
}

private fun DrawScope.drawEntrance() {
    drawRect(
        color = Color(0xFFA5D6A7),
        topLeft = Offset(40f, size.height - 150f),
        size = Size(200f, 120f)
    )
}

private fun DrawScope.drawBar() {
    drawRect(
        color = Color(0xFFFFCC80),
        topLeft = Offset(size.width - 300f, 40f),
        size = Size(260f, 200f)
    )
}

private fun DrawScope.drawWC() {
    drawRect(
        color = Color(0xFF90CAF9),
        topLeft = Offset(40f, 40f),
        size = Size(200f, 140f)
    )
}

private fun DrawScope.drawStairs() {
    drawRect(
        color = Color(0xFFCE93D8),
        topLeft = Offset(size.width - 250f, size.height - 250f),
        size = Size(200f, 200f)
    )
}

private fun DrawScope.drawTables() {
    val tableColor = Color(0xFFBCAAA4)

    val tables = listOf(
        Offset(400f, 300f),
        Offset(550f, 300f),
        Offset(400f, 450f),
        Offset(550f, 450f)
    )

    tables.forEach { pos ->
        drawCircle(
            color = tableColor,
            radius = 50f,
            center = pos
        )
    }
}

private fun DrawScope.drawNode(node: Node) {
    drawCircle(
        color = Color.Blue,
        radius = 15f,
        center = Offset(node.x.toFloat(), node.y.toFloat())
    )

    drawContext.canvas.nativeCanvas.apply {
        drawText(
            node.id,
            node.x.toFloat() + 20f,
            node.y.toFloat() - 20f,
            Paint().apply {
                color = android.graphics.Color.BLACK
                textSize = 35f
            }
        )
    }
}
