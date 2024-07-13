package eu.tkacas.jslearner.presentation.ui.component.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.data.model.QuestionType
import eu.tkacas.jslearner.domain.model.QuestionUI
import eu.tkacas.jslearner.presentation.ui.theme.GreenPal
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.ui.theme.RedPal
import androidx.compose.foundation.layout.size
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

@Composable
fun QuizLayout(
    questionNumber: Int,
    totalQuestions: Int,
    questions: List<QuestionUI>,
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


            val currentQuestion = questions[currentIndex]
            when (currentQuestion.questionType) {
                QuestionType.TRUE_FALSE -> {
                    Text(
                        text = questions[currentIndex].questionDescription,
                        style = TextStyle(fontSize = 20.sp)
                    )
                    TrueFalse(
                        isTrue = null,
                        onTrueFalseSelected = { /* Handle selection */ }
                    )
                }

                QuestionType.MULTIPLE_CHOICE -> {
                    Text(
                        text = questions[currentIndex].questionDescription,
                        style = TextStyle(fontSize = 20.sp)
                    )
                    MultipleChoiceMultipleAnswers(
                        question = currentQuestion,
                        selectedOptions = emptySet(), // Correctly initialized as an empty Set
                        onOptionSelected = { _, _ -> /* Handle selection */ }
                    )
                }

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
                    if (currentIndex == questions.lastIndex)
                        Text("Submit")
                    else
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
