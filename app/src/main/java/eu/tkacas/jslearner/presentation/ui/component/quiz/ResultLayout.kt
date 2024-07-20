package eu.tkacas.jslearner.presentation.ui.component.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.quiz.QuestionResult
import eu.tkacas.jslearner.presentation.ui.theme.GreenPal
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.ui.theme.RedPal
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

@Composable
fun ResultLayout(
    questions: List<QuestionResult>,
    totalScore: Int,
    onQuestionSelected: (Int) -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DisplayScore(totalScore, questions)
            LazyColumn {
                itemsIndexed(questions) { index, question ->
                    QuestionItem(
                        questionNumber = index + 1,
                        isCorrect = question.isCorrect,
                        onQuestionSelected = { onQuestionSelected(index) }
                    )
                }
            }

        }
    }
}

@Composable
fun QuestionItem(
    questionNumber: Int,
    isCorrect: Boolean,
    onQuestionSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onQuestionSelected)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.question) + " $questionNumber",
                fontWeight = FontWeight.Bold,
                color = PrussianBlue
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = if (isCorrect) GreenPal else RedPal,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (isCorrect) stringResource(id = R.string.correct) else stringResource(
                        id = R.string.incorrect
                    ),
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun DisplayScore(
    courseScore: Int,
    questions: List<QuestionResult>
) {
    val correctAnswers = questions.count { it.isCorrect }
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.your_score_in_this_course_is),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$courseScore", fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (correctAnswers >= questions.size / 2) {
            Image(
                painter = painterResource(id = R.drawable.element_teacher_pass),
                contentDescription = stringResource(id = R.string.pass),
                modifier = Modifier
                    .size(200.dp, 200.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.great_job),
                fontSize = 16.sp,
                color = SkyBlue
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.element_teacher_fail),
                contentDescription = stringResource(id = R.string.fail),
                modifier = Modifier
                    .size(200.dp, 200.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.you_can_do_better),
                fontSize = 16.sp,
                color = SkyBlue
            )
        }
    }
}