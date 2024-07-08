package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.presentation.viewmodel.main.StartLessonViewModel
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent


@Composable
fun StartLessonScreen(
    navController: NavController,
    viewModel: StartLessonViewModel,
    id: String
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadLesson(id)
    }

    when (uiState) {
        is Result.Success -> {
            val lesson = (uiState as Result.Success<Lesson>).result
            Text(text = "Lesson: ${lesson.title}")
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