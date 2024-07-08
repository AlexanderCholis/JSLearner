package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.BulletText
import eu.tkacas.jslearner.presentation.ui.theme.componentShapes
import eu.tkacas.jslearner.presentation.viewmodel.main.StartDescriptionViewModel
import eu.tkacas.jslearner.domain.Result
@Composable
fun StartDescriptionScreen(
    navController: NavController,
    viewModel: StartDescriptionViewModel,
    id: String
) {
    val courseDetails by viewModel.courseDetails.observeAsState()
    val lessonDetails by viewModel.lessonDetails.observeAsState()

    LaunchedEffect(id) {
        viewModel.loadDetails(id)
    }

    when {
        courseDetails is Result.Success -> {
            val course = (courseDetails as Result.Success<Course>).result
            CourseDetailsUI(course)
        }
        lessonDetails is Result.Success -> {
            val lesson = (lessonDetails as Result.Success<Lesson>).result
            LessonDetailsUI(lesson)
        }
        courseDetails is Result.Loading || lessonDetails is Result.Loading -> {
            CircularProgressIndicator()
        }
        else -> {
            Text(text = "Error: Unable to fetch details")
        }
    }
}

@Composable
fun CourseDetailsUI(course: Course) {
    // Implement UI elements to display course details
    Text(text = "Course: ${course.title}")
    // Add more UI elements as needed
}

@Composable
fun LessonDetailsUI(lesson: Lesson) {
    // Implement UI elements to display lesson details
    Text(text = "Lesson: ${lesson.title}")
    // Add more UI elements as needed
}