package eu.tkacas.jslearner.ui.components.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.ui.components.test.DragTheWords


@Composable
fun QuestionLayout(
    questionText: String,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = questionText)
            DragTheWords(
                words = listOf("Word 1", "Word 2", "Word 3"),
                targetWords = listOf("Target 1", "Target 2", "Target 3"),
                userAnswers = listOf("Answer 1", "Answer 2", "Answer 3"),
                onWordDropped = { index, word -> println("Word $word dropped at index $index") }
            )
        }
    }
}