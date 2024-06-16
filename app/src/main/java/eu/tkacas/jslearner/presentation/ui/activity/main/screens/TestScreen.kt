package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.runtime.Composable
import eu.tkacas.jslearner.presentation.ui.component.test.QuestionLayout
import eu.tkacas.jslearner.presentation.ui.component.test.TestLayout
import eu.tkacas.jslearner.presentation.viewmodel.main.TestViewModel

@Composable
fun TestScreen(
    testViewModel: TestViewModel
) {
    TestLayout(
        questionNumber = 1,
        totalQuestions = 10,
        remainingTime = "10:00",
        questionLayout = {
            QuestionLayout(
                questionText = "What is the capital of France?",
            )
        },
        onNextClick = {},
        onHelpClick = {}
    )
}
