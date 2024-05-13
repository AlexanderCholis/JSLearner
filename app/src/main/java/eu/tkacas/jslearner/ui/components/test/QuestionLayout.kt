package eu.tkacas.jslearner.ui.components.test

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun QuestionLayout(
    questionNumber: Int,
    totalQuestions: Int,
    remainingTime: String,
    questionText: String,
    onNextClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    Box{
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Question $questionNumber of $totalQuestions",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Time remaining: $remainingTime",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Text(text = questionText)
            // UserOptions() // Uncomment this when you have the UserOptions composable


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onNextClick) {
                    Text("Next")
                }
                Button(onClick = onHelpClick) {
                    Text("Help")
                }
            }
        }
    }
}

@Composable
fun UserOptions(
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    // Implementation goes here
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true)
@Composable
fun QuestionLayoutPreview() {
    QuestionLayout(
        questionNumber = 1,
        totalQuestions = 10,
        remainingTime = "10:00",
        questionText = "What is the capital of France?",
        onNextClick = {},
        onHelpClick = {}
    )
}

