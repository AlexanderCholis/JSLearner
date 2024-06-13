package eu.tkacas.jslearner.ui.activities.main.screens

import androidx.compose.runtime.Composable
import eu.tkacas.jslearner.ui.components.test.QuestionLayout
import eu.tkacas.jslearner.ui.components.test.TestLayout
import eu.tkacas.jslearner.ui.viewModel.TestViewModel

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
