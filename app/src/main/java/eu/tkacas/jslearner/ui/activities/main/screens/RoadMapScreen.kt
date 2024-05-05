package eu.tkacas.jslearner.ui.activities.main.screens

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.data.models.roadmap.RoadMapNodeStatus
import eu.tkacas.jslearner.data.models.roadmap.StrokeParameters
import eu.tkacas.jslearner.data.models.roadmap.getColor
import eu.tkacas.jslearner.data.models.roadmap.getIcon
import eu.tkacas.jslearner.ui.components.defaults.CircleParametersDefaults
import eu.tkacas.jslearner.ui.components.defaults.LineParametersDefaults
import eu.tkacas.jslearner.ui.components.defaults.MessageBubble
import eu.tkacas.jslearner.ui.components.defaults.RoadMapNode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RoadMapScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Road Map")
                }
            )
        }
    ) { innerPadding ->
        val nodes = listOf(
            RoadMapNodeState(RoadMapNodeStatus.LOCKED, RoadMapNodePosition.FIRST, "Start Here"),
            RoadMapNodeState(RoadMapNodeStatus.IN_PROGRESS, RoadMapNodePosition.MIDDLE, "Module 1"),
            RoadMapNodeState(RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.MIDDLE, "Introduction"),
            RoadMapNodeState(RoadMapNodeStatus.COMPLETED, RoadMapNodePosition.LAST, "Finish")
        )

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), // Fills the Scaffold's content area
            contentAlignment = Alignment.Center // Centers the LazyColumn within the Box
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally, // Centers the content horizontally
                modifier = Modifier.fillMaxWidth() // Ensures LazyColumn fills the Box's width
            ) {
                itemsIndexed(nodes) { index, node ->
                    val nextNodeColor = if (index < nodes.size - 1) nodes[index + 1].status.getColor() else null
                    val lineParameters = if (node.position != RoadMapNodePosition.LAST) {
                        LineParametersDefaults.linearGradient(
                            startColor = node.status.getColor(),
                            endColor = nextNodeColor ?: Color.DarkGray
                        )
                    } else null

                    RoadMapNode(
                        nodeState = node,
                        circleParameters = CircleParametersDefaults.circleParameters(
                            backgroundColor = node.status.getColor(),
                            stroke = StrokeParameters(color = node.status.getColor(), width = 2.dp),
                            icon = node.status.getIcon()
                        ),
                        lineParameters = lineParameters,
                        content = { modifier ->
                            node.message?.let {
                                MessageBubble(
                                    modifier,
                                    containerColor = node.status.getColor(),
                                    text = it
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

