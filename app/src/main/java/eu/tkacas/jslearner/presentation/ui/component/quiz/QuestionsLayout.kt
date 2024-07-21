package eu.tkacas.jslearner.presentation.ui.component.quiz

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.data.model.QuestionType
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI
import eu.tkacas.jslearner.presentation.ui.component.HintCard
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

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
    var showHint by rememberSaveable(key = currentIndex.toString()) { mutableStateOf(false) }
    var hint by rememberSaveable { mutableStateOf("") }

    val progress by animateFloatAsState(
        targetValue = (currentIndex + 1) / (totalQuestions.toFloat()), label = ""
    )
    LaunchedEffect(key1 = currentIndex) {
        hint = questions[currentIndex].hint
        showHint = false
    }

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
                    text = stringResource(id = R.string.question) + " $questionNumber " + stringResource(
                        id = R.string.of
                    ) + " $totalQuestions",
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
                        questionIndex = currentIndex,
                        selectedOption = selectedOptions[currentIndex]?.firstOrNull()
                            ?.let { it == "True" },
                        onTrueFalseSelected = { isTrue ->
                            onOptionSelected(currentIndex, listOf(isTrue.toString()))
                        }
                    )
                }

                QuestionType.MULTIPLE_CHOICE -> {
                    if (currentQuestion.correctAnswers.size > 1) {
                        MultipleChoiceMultipleAnswers(
                            questionIndex = currentIndex,
                            options = currentQuestion.options,
                            selectedOptions = selectedOptions[currentIndex] ?: emptyList(),
                            onOptionSelected = { option, isSelected ->
                                val updatedOptions =
                                    selectedOptions[currentIndex]?.toMutableList() ?: mutableListOf()
                                if (isSelected) {
                                    updatedOptions.add(option)
                                } else {
                                    updatedOptions.remove(option)
                                }
                                onOptionSelected(currentIndex, updatedOptions)
                            }
                        )
                    } else {
                        MultipleChoiceSingleAnswer(
                            questionIndex = currentIndex,
                            options = currentQuestion.options,
                            initialSelectedOption = selectedOptions[currentIndex]?.firstOrNull(),
                            onOptionSelected = { option ->
                                onOptionSelected(currentIndex, listOf(option))
                            }
                        )
                    }

                }

                QuestionType.FILL_IN_THE_BLANKS -> {
                    FillInTheBlank(
                        questionIndex = currentIndex,
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
                if (showHint && hint.isNotEmpty()) {
                    HintCard(showHint = showHint, hint = hint)
                }
                Spacer(modifier = Modifier.height(20.dp))
                LinearProgressIndicator(progress = progress)
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            showHint = !showHint
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = SkyBlue)
                    ) {
                        Text(text = stringResource(id = R.string.hint))
                    }
                    Button(
                        onClick = onNextClick,
                        colors = ButtonDefaults.buttonColors(containerColor = PrussianBlue)
                    ) {
                        if (currentIndex == questions.lastIndex)
                            Text(text = stringResource(id = R.string.submit))
                        else {
                            Image(
                                painter = painterResource(id = R.drawable.next),
                                contentDescription = stringResource(id = R.string.next)
                            )
                        }
                    }
                }
            }
        }
    }
}

