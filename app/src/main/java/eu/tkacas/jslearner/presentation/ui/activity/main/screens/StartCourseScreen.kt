package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.roadmap.CourseWithLessons
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartCourseViewModel

@Composable
fun StartCourseScreen(
    navController: NavController,
    viewModel: StartCourseViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val id = sharedViewModel.selectedCourseId.value
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.loadCourse(id)
        }
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
                            navController.navigateUp()
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
                            painter = painterResource(id = R.drawable.element_learning),
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
                                    sharedViewModel.setSelectedLessonId(result.lessons.first().id)
                                    navController.navigate("startLesson")
                                }
                            )
                        }
                    }
                }
            }
        }

        is Result.Error -> {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    BackAppTopBar(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        onBackClick = {
                            navController.navigateUp()
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
                        text = error.message ?: stringResource(id = R.string.an_error_occurred),
                    )
                }
            }
        }
    }
}
