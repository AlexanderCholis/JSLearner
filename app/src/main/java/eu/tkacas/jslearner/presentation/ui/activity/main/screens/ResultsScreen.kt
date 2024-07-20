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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.quiz.ResultLayout
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel

@Composable
fun ResultsScreen(
    navController: NavController,
    sharedViewModel: MainSharedViewModel
) {

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
                ResultLayout(
                    questions = sharedViewModel.quizResults.value!!.questionResults,
                    totalScore = sharedViewModel.quizResults.value!!.score,
                    onQuestionSelected = { index ->
                        // TODO: Implement navigation to the question
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                navController.navigateUp()
                                // TODO: Implement navigation to the quiz
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrussianBlue)
                        ) {
                            Text(text = stringResource(id = R.string.restart_quiz))
                        }
                        Button(
                            onClick = {
                                navController.navigate("roadmap")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrussianBlue)
                        ) {
                            Text(text = stringResource(id = R.string.end_quiz))
                        }
                    }
                }
            }
        }
    }
}