package eu.tkacas.jslearner.ui.components.test

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun QuestionLayout(
    questionText: String,
    userOptions: List<String>
) {
    Text(text = questionText)
    // UserOptions() // Uncomment this when you have the UserOptions composable

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
        questionText = "What is the capital of France?",
        userOptions = listOf("Paris", "London", "Berlin", "Madrid")
    )
}

