package eu.tkacas.jslearner.presentation.ui.component.quiz

import android.annotation.SuppressLint
import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.LightBeige

@Composable
private fun MultipleChoiceSingleCard(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    enableOption: Boolean,
    isCorrect: Boolean
) {
    val cardColor = when {
        isCorrect -> Color.Green // Mark correct options with green
        !enableOption -> Color.LightGray // Locked options with light gray
        isSelected -> SkyBlue
        else -> Color.White
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = enableOption, onClick = onSelected) // Disable click when not enabled
            .padding(6.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            RadioButton(
                selected = isSelected,
                onClick = if (enableOption) onSelected else null,
                colors = RadioButtonDefaults.colors(
                    selectedColor = PrussianBlue
                )
            )
            Text(
                text = text,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}

@Composable
private fun MultipleChoiceMultipleCard(
    text: String,
    isSelected: MutableState<Boolean>,
    onSelected: () -> Unit,
    enableOption: Boolean,
    isCorrect: Boolean
) {
    val cardColor = when {
        isCorrect -> Color.Green // Mark correct options with green
        !enableOption -> Color.LightGray // Locked options with light gray
        isSelected.value -> SkyBlue
        else -> Color.White
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = enableOption, onClick = onSelected) // Disable click when not enabled
            .padding(6.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            Checkbox(
                checked = isSelected.value,
                onCheckedChange = if (enableOption) {
                    { isSelected.value = it }
                } else {
                    null
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = PrussianBlue
                )
            )
            Text(
                text = text,
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}


@Composable
fun MultipleChoiceSingleAnswer(
    questionIndex: Int,
    options: List<String>,
    initialSelectedOption: String?,
    onOptionSelected: (String) -> Unit,
    correctOptions: List<String> = emptyList(),
    wrongOptions: List<String> = emptyList(),
    enableOptions: Boolean = true
) {
    var selectedOption by remember("$questionIndex-${initialSelectedOption.hashCode()}") { mutableStateOf(initialSelectedOption) }

    Column {
        options.forEach { option ->
            MultipleChoiceSingleCard(
                text = option,
                isSelected = option == selectedOption,
                onSelected = {
                    selectedOption = option
                    onOptionSelected(option)
                },
                enableOption = enableOptions,
                isCorrect = option in correctOptions
            )
        }
    }
}

@Composable
fun MultipleChoiceMultipleAnswers(
    questionIndex: Int,
    options: List<String>,
    selectedOptions: List<String>?,
    onOptionSelected: (String, Boolean) -> Unit,
    correctOptions: List<String> = emptyList(),
    wrongOptions: List<String> = emptyList(),
    enableOptions: Boolean = true
) {
    val safeSelectedOptions = selectedOptions ?: emptyList()
    Column {
        options.forEach { option ->
            val key = "$questionIndex-${option.hashCode()}"
            val isSelected = remember(key) { mutableStateOf(option in safeSelectedOptions) }

            MultipleChoiceMultipleCard(
                text = option,
                isSelected = isSelected,
                onSelected = {
                    isSelected.value = !isSelected.value
                    onOptionSelected(option, isSelected.value)
                },
                enableOption = enableOptions,
                isCorrect = option in correctOptions
            )
        }
    }
}


@Composable
fun TrueFalse(
    questionIndex: Int,
    selectedOption: Boolean?,
    onTrueFalseSelected: (Boolean) -> Unit,
    correctOptions: List<String> = emptyList(),
    wrongOptions: List<String> = emptyList(),
    enableOptions: Boolean = true
) {
    // Convert boolean values to "True" or "False" strings
    val options = listOf("True", "False")
    val initialSelectedOption = when (selectedOption) {
        true -> "True"
        false -> "False"
        else -> null
    }

    // Call MultipleChoiceSingleAnswer with updated parameters
    MultipleChoiceSingleAnswer(
        questionIndex = questionIndex,
        options = options,
        initialSelectedOption = initialSelectedOption,
        onOptionSelected = { option ->
            onTrueFalseSelected(option == "True")
        },
        correctOptions = correctOptions,
        wrongOptions = wrongOptions,
        enableOptions = enableOptions
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableWordCard(text: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(4.dp)
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = {
                        startTransfer(
                            DragAndDropTransferData(
                                ClipData.newPlainText("text/plain", text)
                            )
                        )
                    }
                )
            }
    ) {
        Text(text = text, modifier = Modifier.padding(8.dp))
    }
}

@SuppressLint("RememberReturnType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TargetWordBox(
    text: String,
    onDrop: (String) -> Unit
) {
    var textState by remember { mutableStateOf(text) }
    var backgroundColor by remember { mutableStateOf(Color.LightGray) }
    val dragAndDropTarget = remember {
        object : DragAndDropTarget {
            override fun onDrop(event: DragAndDropEvent): Boolean {
                val draggedData = event.toAndroidDragEvent()
                    .clipData.getItemAt(0).text.toString()
                onDrop(draggedData)
                textState = draggedData
                return true
            }

            override fun onEntered(event: DragAndDropEvent) {
                super.onEntered(event)
                backgroundColor = LightBeige
            }

            override fun onExited(event: DragAndDropEvent) {
                super.onExited(event)
                backgroundColor = Color.LightGray
            }
        }
    }

    Surface(
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor,
        modifier = Modifier
            .padding(4.dp)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { event ->
                    event.mimeTypes().contains("text/plain")
                },
                target = dragAndDropTarget
            )
    ) {
        val textModifier = if (textState.isEmpty()) {
            Modifier
                .padding(8.dp)
                .widthIn(min = 60.dp)
        } else {
            Modifier.padding(8.dp)
        }
        Text(text = if (textState.isEmpty()) " " else textState, modifier = textModifier)
    }
}

@Composable
fun FillInTheBlank(
    options: List<String>,
    selectedOption: String?,
    onAnswerSelected: (String) -> Unit
) {
    val optionKey = options.joinToString(separator = "")
    val key = optionKey.hashCode().toString()
    var answer by remember(key) { mutableStateOf(selectedOption ?: "") }

    Column {
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.your_answer_is),
                style = TextStyle(fontSize = 16.sp)
            )
            TargetWordBox(
                text = answer,
                onDrop = { droppedText ->
                    answer = droppedText
                    onAnswerSelected(droppedText)
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Display options as draggable cards
            options.forEach { option ->
                DraggableWordCard(text = option)
            }
        }
    }
}
