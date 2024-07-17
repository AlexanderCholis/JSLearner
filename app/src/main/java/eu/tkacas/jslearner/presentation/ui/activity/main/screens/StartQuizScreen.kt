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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.quiz.Quiz
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartQuizViewModel

@Composable
fun StartQuizScreen(
    navController: NavController,
    viewModel: StartQuizViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val id = sharedViewModel.selectedLessonId.value
    val lesson = sharedViewModel.selectedLesson.value
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.loadQuiz(id)
        }
    }

    when (uiState) {
        is Result.Loading -> {
            ProgressIndicatorComponent()
        }

        is Result.Success -> {
            val quiz = (uiState as Result.Success<Quiz>).result
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    BackAppTopBar(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        onBackClick = {
                            navController.navigateUp()
                        },
                        title = lesson?.title ?: ""
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
                            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 28.dp)
                    ) {
                        Text(
                            text = quiz.questions.size.toString() + stringResource(id = R.string.questions),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = stringResource(id = R.string.this_quiz_will_test_your_knowledge),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(100.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.element_questions),
                                contentDescription = stringResource(id = R.string.simple_image),
                                modifier = Modifier.size(240.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(48.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.ready_to_test_your_knowledge),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GeneralButtonComponent(
                                valueId = R.string.start_quiz,
                                onButtonClicked = {
                                    sharedViewModel.setSelectedQuiz(quiz)
                                    navController.navigate("quiz")
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