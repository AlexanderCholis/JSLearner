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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI
import eu.tkacas.jslearner.presentation.ui.theme.GreenPal
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.ui.theme.RedPal
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

@Composable
fun ResultLayout(
    questions: List<QuestionUI>,
    totalScore: Int,
    onQuestionSelected: (Int) -> Unit // Add this parameter
) {
    Column {
        DisplayScore(totalScore)
        LazyColumn {
            itemsIndexed(questions) { index, question ->
                val isCorrect = true // This logic should be replaced with actual correctness check
                QuestionItem(
                    questionNumber = index + 1,
                    isCorrect = isCorrect,
                    onQuestionSelected = { onQuestionSelected(index) } // Use the parameter here
                )
            }
        }
    }
}

@Composable
fun QuestionItem(
    questionNumber: Int,
    isCorrect: Boolean,
    onQuestionSelected: () -> Unit // Add this parameter
) { // Show if the question is correct or not
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onQuestionSelected)
    ) {
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