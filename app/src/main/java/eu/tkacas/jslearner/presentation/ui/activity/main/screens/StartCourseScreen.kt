package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.viewmodel.main.StartCourseViewModel
import eu.tkacas.jslearner.domain.Result


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
        is Result.Success -> {
            val course = (uiState as Result.Success<Course>).result
            Text(text = "Course: ${course.title}")
        }
        is Result.Error -> {
            val error = (uiState as Result.Error).exception
            error.message?.let { Text(text = it) }
        }
        Result.Loading -> {
            ProgressIndicatorComponent()
        }
    }
}