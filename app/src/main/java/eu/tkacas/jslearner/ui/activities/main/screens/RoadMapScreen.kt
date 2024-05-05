package eu.tkacas.jslearner.ui.activities.main.screens

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import eu.tkacas.jslearner.data.repositories.RoadmapRepository
import eu.tkacas.jslearner.ui.components.defaults.CircleParametersDefaults
import eu.tkacas.jslearner.ui.components.defaults.LineParametersDefaults
import eu.tkacas.jslearner.ui.components.defaults.MessageBubble
import eu.tkacas.jslearner.ui.components.defaults.RoadMapNode
import eu.tkacas.jslearner.ui.viewModel.RoadMapViewModel

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RoadMapScreen(viewModel: RoadMapViewModel = viewModel()) {
    val nodes by viewModel.nodes.collectAsState()

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
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
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



