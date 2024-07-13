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

@Composable
fun QuestionItem(questionNumber: Int, isCorrect: Boolean) { // Show if the question is correct or not
    Column(modifier = Modifier.padding(8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Question $questionNumber", fontWeight = FontWeight.Bold, color = PrussianBlue)
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = if (isCorrect) GreenPal else RedPal, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (isCorrect) "Correct" else "Incorrect",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun DisplayScore(coursescore: Int) { // Display the score of the course
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Your Score in this Course is:", fontWeight = FontWeight.Bold)
        Text(text = "$coursescore", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        if (coursescore == 0) {
            Image(painter = painterResource(id = R.drawable.element_teacher_fail), contentDescription = "Fail", modifier = Modifier.size(200.dp, 200.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "You can do better!", fontSize = 16.sp, color = SkyBlue)
        } else {
            Image(painter = painterResource(id = R.drawable.element_teacher_pass), contentDescription = "Pass", modifier = Modifier.size(200.dp, 200.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Great Job!", fontSize = 16.sp, color = SkyBlue)
        }
    }
}
