package eu.tkacas.jslearner.presentation.ui.component.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.data.model.QuestionType
import eu.tkacas.jslearner.domain.model.quiz.QuestionResult
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI
import eu.tkacas.jslearner.presentation.ui.component.HintCard
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue

@Composable
fun AnsweredQuestionLayout(
    questionIndex: Int,
    selectedOptions: Map<Int, List<String>>?,
    question: QuestionUI,
    questionResult: QuestionResult,
    onButtonClick: () -> Unit
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = question.questionDescription,
                style = TextStyle(fontSize = 20.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            when (question.questionType) {
                QuestionType.TRUE_FALSE -> {
                    TrueFalse(
                        questionIndex = questionIndex,
                        selectedOption = selectedOptions?.get(questionIndex)?.firstOrNull()
                            .toBoolean(),
                        onTrueFalseSelected = { },
                        correctOptions = questionResult.correctOptions,
                        enableOptions = false
                    )
                }

                QuestionType.MULTIPLE_CHOICE -> {
                    if (question.correctAnswers.size > 1) {
                        MultipleChoiceMultipleAnswers(
                            questionIndex = questionIndex,
                            options = question.options,
                            selectedOptions = selectedOptions?.get(questionIndex),
                            onOptionSelected = { _, _ -> },
                            correctOptions = questionResult.correctOptions,
                            enableOptions = false
                        )
                    } else {
                        MultipleChoiceSingleAnswer(
                            questionIndex = questionIndex,
                            options = question.options,
                            initialSelectedOption = selectedOptions?.get(questionIndex)
                                ?.firstOrNull(),
                            onOptionSelected = { _ -> },
                            correctOptions = questionResult.correctOptions,
                            enableOptions = false
                        )
                    }
                }

                QuestionType.FILL_IN_THE_BLANKS -> {
                    FillInTheBlank(
                        questionIndex = questionIndex,
                        options = question.options,
                        selectedOption = selectedOptions?.get(questionIndex)?.firstOrNull(),
                        onAnswerSelected = {},
                        correctOptions = questionResult.correctOptions,
                        enableInteraction = false
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
                HintCard(showHint = true, hint = question.hint)
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(containerColor = PrussianBlue)
                ) {
                    Text(text = stringResource(id = R.string.back_to_results))
                }
            }
        }
    }
}