package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.presentation.viewmodel.welcome.RoadMapViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.domain.entity.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.domain.entity.roadmap.StrokeParameters
import eu.tkacas.jslearner.domain.entity.roadmap.getColor
import eu.tkacas.jslearner.domain.entity.roadmap.getIcon
import eu.tkacas.jslearner.presentation.ui.component.default.CircleParametersDefaults
import eu.tkacas.jslearner.presentation.ui.component.default.LineParametersDefaults
import eu.tkacas.jslearner.presentation.ui.component.default.MessageBubble
import eu.tkacas.jslearner.presentation.ui.component.default.RoadMapNode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RoadMapScreen() {
    /*val roadmapRepository = (LocalContext.current.applicationContext as JSLearner).roadmapRepository
    val viewModel: RoadMapViewModel = viewModel(factory = RoadMapViewModel.provideFactory(roadmapRepository))
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
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
            when (uiState) {
                is RoadMapViewModel.RoadMapUiState.Loading -> {
                    CircularProgressIndicator()
                }
                is RoadMapViewModel.RoadMapUiState.Success -> {
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val nodes = (uiState as RoadMapViewModel.RoadMapUiState.Success).nodes
                        itemsIndexed(nodes) { index, node ->
                            val nextNodeColor = if (index < nodes.size - 1) nodes[index + 1].status.getColor() else Color.DarkGray
                            val lineParameters = if (node.position != RoadMapNodePosition.LAST) {
                                LineParametersDefaults.linearGradient(
                                    startColor = node.status.getColor(),
                                    endColor = nextNodeColor
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
                                            text = it,
                                            onClick = {
                                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                            }
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
                is RoadMapViewModel.RoadMapUiState.Error -> {
                    Text("Error: ${(uiState as RoadMapViewModel.RoadMapUiState.Error).message}", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }*/
}
