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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.data.model.QuestionType

@Composable
fun QuizLayout(
    questionNumber: Int,
    totalQuestions: Int,
    questions: List<Question>,
    currentIndex: Int,
    onNextClick: () -> Unit,
) {
    val hint by rememberSaveable { mutableStateOf(questions[currentIndex].hint) }
    val showHint = rememberSaveable { mutableStateOf(false) }

    Box {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Question $questionNumber of $totalQuestions",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = questions[currentIndex].questionDescription,
                style = TextStyle(fontSize = 20.sp)
            )

            val currentQuestion = questions[currentIndex]
            when (currentQuestion.questionType) {
                QuestionType.TRUE_FALSE -> TrueFalse(
                    isTrue = null,
                    onTrueFalseSelected = { /* Handle selection */ }
                )

                QuestionType.MULTIPLE_CHOICE -> MultipleChoiceMultipleAnswers(
                    options = (currentQuestion.options as List<String>),
                    selectedOptions = emptySet(), // Correctly initialized as an empty Set
                    onOptionSelected = { _, _ -> /* Handle selection */ }
                )

                QuestionType.FILL_IN_THE_BLANKS -> FillInTheBlanks(
                    question = currentQuestion,
                    onAnswerSelected = { /* Handle answer change */ }
                )

                else -> Text("Unsupported question type")
            }
            Row {
                Text(
                    text = if (showHint.value) hint else "",
                    style = TextStyle(fontSize = 16.sp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onNextClick) {
                    Text("Next")
                }
                Button(
                    onClick = {
                        showHint.value = !showHint.value
                    }
                ) {
                    Text("Hint")
                }
            }
        }
    }
}