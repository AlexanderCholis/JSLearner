package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.quiz.AnsweredQuestionLayout
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel

@Composable
fun AnsweredQuestionScreen(
    navController: NavController,
    sharedViewModel: MainSharedViewModel,
    questionIndex: Int
) {
    val quiz = sharedViewModel.selectedQuiz.value
    val quizResults = sharedViewModel.quizResults.value
    val selectedOptions = sharedViewModel.selectedQuestionOptions.value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                color = Color.White,
                onBackClick = {},
                isBackEnabled = false
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnsweredQuestionLayout(
                    questionIndex = questionIndex,
                    selectedOptions = selectedOptions,
                    question = quiz!!.questions[questionIndex],
                    questionResult = quizResults!!.questionResults[questionIndex],
                    onButtonClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}
