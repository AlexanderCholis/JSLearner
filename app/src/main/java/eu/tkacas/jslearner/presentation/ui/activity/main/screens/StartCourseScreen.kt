package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.viewmodel.main.StartCourseViewModel
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.roadmap.CourseWithLessons
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent

@Composable
fun StartCourseScreen(
    navController: NavController,
    viewModel: StartCourseViewModel,
    id: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadCourse(id)
    }

    when (uiState) {
        is Result.Loading -> {
            ProgressIndicatorComponent()
        }
        is Result.Success -> {
            val result = (uiState as Result.Success<CourseWithLessons>).result
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    BackAppTopBar(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        onBackClick = {
                            navController.popBackStack(navController.graph.startDestinationId, inclusive = false)
                            navController.navigate("roadmap")
                        },
                        title = result.course.title
                    )
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(innerPadding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = result.course.descriptionLong,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.application),
                            contentDescription = stringResource(id = R.string.simple_image),
                            modifier = Modifier
                                .size(240.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = "Course Curriculum",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(modifier = Modifier.fillMaxWidth()) {
                            result.lessons.forEach { lesson ->
                                Text(
                                    text = "• ${lesson.title}",
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            GeneralButtonComponent(
                                valueId = R.string.start_course,
                                onButtonClicked = {
                                    //TODO: navigate to the lesson
                                }
                            )
                        }
                    }
                }
            }
        }
        is Result.Error -> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    BackAppTopBar(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        onBackClick = {
                            navController.popBackStack(navController.graph.startDestinationId, inclusive = false)
                            navController.navigate("roadmap")
                        }
                    )
                }
            ) { innerPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(innerPadding)
                ) {
                    val error = (uiState as Result.Error).exception
                    Text(
                        text = error.message ?: "An error occurred",
                    )
                }
            }
        }
    }
}
