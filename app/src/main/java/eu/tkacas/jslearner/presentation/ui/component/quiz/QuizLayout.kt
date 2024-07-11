package eu.tkacas.jslearner.presentation.ui.component.quiz

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
import eu.tkacas.jslearner.data.model.Question

@Composable
fun QuizLayout(
    questionNumber: Int,
    totalQuestions: Int,
    questions: List<Question>,
    currentIndex: Int,
    onNextClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    Box {
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
            }

            val currentQuestion = questions[currentIndex]
            when (currentQuestion.questionType) {
                "true_false" -> TrueFalse(
                    isTrue = null, // Assuming a way to determine if the user has selected true or false
                    onTrueFalseSelected = { /* Handle selection */ }
                )
                "multiple_choice_single_answer" -> MultipleChoiceSingleAnswer(
                    options = currentQuestion.options.map { it["text"] ?: "" }, // Assuming options is a list of maps with a "text" key
                    initialSelectedOption = null, // Assuming a way to determine the initially selected option if any
                    onOptionSelected = { /* Handle selection */ }
                )
                "multiple_choice_multiple_answers" -> MultipleChoiceMultipleAnswers(
                    options = currentQuestion.options.map { it["text"] ?: "" }, // Assuming options is a list of maps with a "text" key
                    selectedOptions = emptySet(), // Assuming a way to determine which options are initially selected
                    onOptionSelected = { _, _ -> /* Handle selection */ }
                )
                "matching" -> DynamicDragAndDrop(
                    optionsA = currentQuestion.options.map { it["text"] ?: "" }, // Assuming options is a list of maps with a "text" key
                    optionsB = currentQuestion.options.map { it["text"] ?: "" }, // Assuming options is a list of maps with a "text" key
                    //correctAnswers = currentQuestion.correctAnswers.subList(0, currentQuestion.options.size), // Assuming correctAnswers is a list of maps with a "text" key
                )
                "fill_in_the_blanks" -> FillInTheBlanks(
                    question = currentQuestion,
                    onAnswerSelected = { /* Handle answer change */ }
                )
                else -> Text("Unsupported question type")
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