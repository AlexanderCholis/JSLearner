package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.QuizViewModel
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.domain.model.Quiz

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val previousRoute = navController.previousBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        val lessonId = sharedViewModel.selectedLessonId.value
        if (lessonId != null) {
            viewModel.loadQuiz(lessonId)
        }
    }

    when(uiState) {
        is Result.Loading -> {
            ProgressIndicatorComponent()
        }

        is Result.Success -> {
            val quiz = (uiState as Result.Success<Quiz>).result
            var currentIndex by rememberSaveable { mutableIntStateOf(if (previousRoute == "startQuiz") 0 else quiz.questions.size) }

            val progress by animateFloatAsState(
                targetValue = (currentIndex + 1) / (quiz.questions.size.toFloat()), label = ""
            )

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    BackAppTopBar(
                        color = Color.White,
                        onBackClick = {
                            if (currentIndex > 0) {
                                currentIndex--
                            } else {
                                navController.navigateUp()
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Surface(
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(innerPadding)
                ) {
                    Column {
                        Spacer(modifier = Modifier.padding(10.dp))
                        NormalText(text = quiz.questions[currentIndex].questionDescription)
                        Spacer(modifier = Modifier.weight(1f))
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LinearProgressIndicator(progress = progress)
                            Spacer(modifier = Modifier.padding(20.dp))
                            GeneralButtonComponent(valueId = R.string.next, onButtonClicked = {
                                if (currentIndex < quiz.questions.size - 1) {
                                    currentIndex++
                                } else {
                                    //navController.navigate("") //TODO: Add destination
                                    null
                                }
                            })
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
                        color = Color.White,
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
                    Text(text = error.message ?: stringResource(id = R.string.an_error_occurred))
                }
            }
        }
    }
}