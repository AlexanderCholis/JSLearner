package eu.tkacas.jslearner.ui.components.test

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TestLayout(
    questionNumber: Int,
    totalQuestions: Int,
    remainingTime: String,
    questionLayout: @Composable () -> Unit,
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
                modifier = Modifier.fillMaxWidth().padding(20.dp),
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

            Box(modifier = Modifier.padding(16.dp)) {
                questionLayout()
            }
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

@Preview
@Composable
fun TestLayoutPreview() {
    TestLayout(
        questionNumber = 1,
        totalQuestions = 10,
        remainingTime = "10:00",
        questionLayout = {
            Text("What is the capital of France?")
        },
        onNextClick = {},
        onHelpClick = {}
    )
}