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
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.presentation.viewmodel.main.StartLessonViewModel
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.ui.theme.componentShapes
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel


@Composable
fun StartLessonScreen(
    navController: NavController,
    viewModel: StartLessonViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val id = sharedViewModel.selectedLessonId.value
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.loadLesson(id)
        }
    }

    when (uiState) {
        is Result.Loading -> {
            ProgressIndicatorComponent()
        }
        is Result.Success -> {
            val lesson = (uiState as Result.Success<Lesson>).result
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    BackAppTopBar(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        onBackClick = {
                            navController.navigateUp()
                        },
                        title = lesson.title
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
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 16.dp, start = 36.dp, end = 36.dp, bottom = 16.dp)
                        ) {
                            Text(
                                text = lesson.description,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            /*subsections.forEach {
                                BulletText(value = it)
                            }*/ //TODO: add Lessons
                            Spacer(modifier = Modifier.height(100.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.application),
                                    contentDescription = stringResource(id = R.string.simple_image),
                                    modifier = Modifier.size(240.dp)
                                )
                            }

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                GeneralButtonComponent(
                                    valueId = R.string.start_lesson,
                                    onButtonClicked = {
                                        navController.navigate("lesson")
                                    }
                                )
                            }
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
                    Text(text = error.message ?: "An error occurred")
                }
            }
        }
    }
}