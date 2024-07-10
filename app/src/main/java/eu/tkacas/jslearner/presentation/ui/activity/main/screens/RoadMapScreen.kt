package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeCategory
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeStatus
import eu.tkacas.jslearner.domain.model.roadmap.StrokeParameters
import eu.tkacas.jslearner.domain.model.roadmap.getColor
import eu.tkacas.jslearner.domain.model.roadmap.getIcon
import eu.tkacas.jslearner.presentation.ui.component.FloatingButton
import eu.tkacas.jslearner.presentation.ui.component.MenuAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.NavigationDrawer
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.ui.component.default.CircleParametersDefaults
import eu.tkacas.jslearner.presentation.ui.component.default.LineParametersDefaults
import eu.tkacas.jslearner.presentation.ui.component.default.MessageBubble
import eu.tkacas.jslearner.presentation.ui.component.default.RoadMapNode
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.RoadMapViewModel
import kotlinx.coroutines.launch

@Composable
internal fun RoadMapScreen(
    navController: NavController,
    viewModel: RoadMapViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                drawerState = drawerState,
                getNavigationDrawerItemsUseCase = JSLearner.appModule.getNavigationDrawerItemsUseCase,
                logoutUseCase = JSLearner.appModule.logoutUseCase
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                MenuAppTopBar(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onMenuClick = {
                        scope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    },
                    title = stringResource(id = R.string.roadmap),
                    drawerState = drawerState,
                    score = (uiState as? Result.Success)?.result?.user?.experienceScore ?: 0
                )
            },
            floatingActionButton = {
                FloatingButton(
                    onButtonClicked = {
                        sharedViewModel.setUser((uiState as Result.Success).result.user)
                        sharedViewModel.setCoursesState((uiState as Result.Success).result.nodes?.filter { it.category == RoadMapNodeCategory.COURSE }
                            ?: emptyList())
                        navController.navigate("coursesPath")
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
                    is Result.Loading -> {
                        ProgressIndicatorComponent()
                    }

                    is Result.Success -> {
                        val nodes = (uiState as Result.Success).result.nodes
                        if (nodes != null) {  // Handle nullability
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 8.dp)
                            ) {
                                LazyColumn(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    itemsIndexed(nodes) { index, node ->
                                        val nextNodeColor =
                                            if (index < nodes.size - 1) nodes[index + 1].status.getColor() else Color.DarkGray
                                        val lineParameters =
                                            if (node.position != RoadMapNodePosition.LAST) {
                                                LineParametersDefaults.linearGradient(
                                                    startColor = node.status.getColor(),
                                                    endColor = nextNodeColor
                                                )
                                            } else null
                                        RoadMapNode(
                                            nodeState = node,
                                            circleParameters = CircleParametersDefaults.circleParameters(
                                                backgroundColor = node.status.getColor(),
                                                stroke = StrokeParameters(
                                                    color = node.status.getColor(),
                                                    width = 2.dp
                                                ),
                                                icon = node.status.getIcon()
                                            ),
                                            lineParameters = lineParameters,
                                            content = { modifier ->
                                                MessageBubble(
                                                    nodeState = node,
                                                    modifier,
                                                    containerColor = node.status.getColor(),
                                                    text = node.title ?: "",
                                                    onClick = {
                                                        if (node.status == RoadMapNodeStatus.LOCKED) {
                                                            Toast.makeText(
                                                                context,
                                                                "Complete previous lessons to unlock this ${node.category.name.lowercase()}.",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        } else {
                                                            when (node.category) {
                                                                RoadMapNodeCategory.LESSON -> {
                                                                    sharedViewModel.setSelectedLessonId(
                                                                        node.id
                                                                    )
                                                                    navController.navigate("startLesson")
                                                                }

                                                                RoadMapNodeCategory.COURSE -> {
                                                                    sharedViewModel.setSelectedCourseId(
                                                                        node.id
                                                                    )
                                                                    navController.navigate("startCourse")
                                                                }

                                                                RoadMapNodeCategory.TEST -> {
                                                                    sharedViewModel.setSelectedQuizId(
                                                                        node.id
                                                                    )
                                                                    navController.navigate("startQuiz?testId=${node.id}")
                                                                }
                                                            }
                                                        }
                                                    }
                                                )
                                            }
                                        )
                                    }
                                }
                            }
                        } else {
                            Text(
                                "No roadmap nodes available",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                    is Result.Error -> {
                        Text(
                            "Error: ${(uiState as Result.Error).exception.message}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
}
