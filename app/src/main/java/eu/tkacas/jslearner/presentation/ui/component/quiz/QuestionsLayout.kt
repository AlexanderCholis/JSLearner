package eu.tkacas.jslearner.presentation.ui.component.quiz

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.data.model.QuestionType
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

@Composable
fun QuestionsLayout(
    questionNumber: Int,
    totalQuestions: Int,
    questions: List<QuestionUI>,
    currentIndex: Int,
    selectedOptions: MutableMap<Int, List<String>>,
    onOptionSelected: (Int, List<String>) -> Unit,
    onNextClick: () -> Unit,
) {
    val hint by rememberSaveable { mutableStateOf(questions[currentIndex].hint) }
    val showHint = rememberSaveable { mutableStateOf(false) }

    val progress by animateFloatAsState(
        targetValue = (currentIndex + 1) / (totalQuestions.toFloat()), label = ""
    )

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.question) + " $questionNumber " + stringResource(id = R.string.of) +" $totalQuestions",
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = questions[currentIndex].questionDescription,
                style = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            val currentQuestion = questions[currentIndex]
            when (currentQuestion.questionType) {
                QuestionType.TRUE_FALSE -> {
                    TrueFalse(
                        selectedOption = selectedOptions[currentIndex]?.firstOrNull()?.let { it == "True" },
                        onTrueFalseSelected = { isTrue ->
                            onOptionSelected(currentIndex, listOf(isTrue.toString()))
                        }
                    )
                }

                QuestionType.MULTIPLE_CHOICE -> {
                    MultipleChoiceMultipleAnswers(
                        options = currentQuestion.options,
                        selectedOptions = selectedOptions[currentIndex] ?: emptyList(),
                        onOptionSelected = { option, isSelected ->
                            val updatedOptions = selectedOptions[currentIndex]?.toMutableList() ?: mutableListOf()
                            if (isSelected) {
                                updatedOptions.add(option)
                            } else {
                                updatedOptions.remove(option)
                            }
                            onOptionSelected(currentIndex, updatedOptions)
                        }
                    )
                }

                QuestionType.FILL_IN_THE_BLANKS -> {
                    FillInTheBlank(
                        options = currentQuestion.options,
                        selectedOption = selectedOptions[currentIndex]?.firstOrNull(),
                        onAnswerSelected = { answer ->
                            onOptionSelected(currentIndex, listOf(answer))
                        }
                    )
                }

                else -> Text(text = stringResource(id = R.string.unsupported_question_type))
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (showHint.value) hint else "",
                    style = TextStyle(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                LinearProgressIndicator(progress = progress)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = onNextClick) {
                        if (currentIndex == questions.lastIndex)
                            Text(text = stringResource(id = R.string.submit))
                        else
                            Text(text = stringResource(id = R.string.next))
                    }
                    Button(
                        onClick = {
                            showHint.value = !showHint.value
                        }
                    ) {
                        Text(text = stringResource(id = R.string.hint))
                    }
                }
            }
        }
    }
}

