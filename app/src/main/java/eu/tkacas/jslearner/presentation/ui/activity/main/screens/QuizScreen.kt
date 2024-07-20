package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.domain.model.quiz.QuizResults
import eu.tkacas.jslearner.presentation.ui.component.quiz.QuestionsLayout
import eu.tkacas.jslearner.presentation.ui.component.quiz.ResultLayout
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.viewmodel.main.QuizViewModel

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val quiz = sharedViewModel.selectedQuiz.value
    val previousRoute = navController.previousBackStackEntry?.destination?.route
    var quizResults by remember { mutableStateOf<QuizResults?>(null) }
    val selectedOptions = rememberSaveable { mutableStateOf(mutableMapOf<Int, List<String>>()) }

    if (quiz != null) {
        var currentIndex by rememberSaveable { mutableIntStateOf(if (previousRoute == "startQuiz") 0 else quiz.questions.size) }

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
                    },
                    isBackEnabled = currentIndex > 0
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
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    QuestionsLayout(
                        questionNumber = currentIndex + 1,
                        totalQuestions = quiz.questions.size,
                        questions = quiz.questions,
                        currentIndex = currentIndex,
                        selectedOptions = selectedOptions.value,
                        onOptionSelected = { index, options ->
                            selectedOptions.value[index] = options
                        },
                        onNextClick = {
                            if (currentIndex < quiz.questions.size - 1) {
                                currentIndex++
                            } else {
                                val userOptions = selectedOptions.value.values.toList()
                                quizResults = viewModel.getQuizResults(quiz, userOptions)
                                sharedViewModel.setQuizResults(quizResults!!)
                                navController.navigate("results")
                            }
                        }
                    )
                }
            }
        }
    }
    else {
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
                Text(text = stringResource(id = R.string.an_error_occurred))
            }
        }
    }
}