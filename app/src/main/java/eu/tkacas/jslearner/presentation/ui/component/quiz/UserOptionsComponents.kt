package eu.tkacas.jslearner.presentation.ui.component.quiz

import android.annotation.SuppressLint
import android.content.ClipData
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.presentation.ui.theme.GreenLightPal
import eu.tkacas.jslearner.presentation.ui.theme.GreenPal
import eu.tkacas.jslearner.presentation.ui.theme.LightBeige

@Composable
fun MultipleChoiceSingleCard(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val cardColor = if (isSelected) SkyBlue else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelected)
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
                onClick = onSelected,
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
fun MultipleChoiceMultipleCard(
    text: String,
    isSelected: MutableState<Boolean>,
    onSelected: () -> Unit
) {
    val cardColor = if (isSelected.value) SkyBlue else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelected)
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
                onCheckedChange = { isSelected.value = it },
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
    options: List<String>,
    initialSelectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    var selectedOption by remember { mutableStateOf(initialSelectedOption) }

    Column {
        options.forEach { option ->
            MultipleChoiceSingleCard(
                text = option,
                isSelected = option == selectedOption,
                onSelected = {
                    selectedOption = option
                    onOptionSelected(option)
                }
            )
        }
    }
}

@Composable
fun MultipleChoiceMultipleAnswers(
    options: List<String>,
    selectedOptions: Set<String>?,
    onOptionSelected: (String, Boolean) -> Unit
) {
    val safeSelectedOptions = selectedOptions ?: emptySet()

    Column {
        options.forEach { option ->
            MultipleChoiceMultipleCard(
                text = option,
                isSelected = remember { mutableStateOf(option in safeSelectedOptions) },
                onSelected = { onOptionSelected(option, option !in safeSelectedOptions) }
            )
        }
    }
}

@Composable
fun TrueFalse(
    isTrue: Boolean?,
    onTrueFalseSelected: (Boolean) -> Unit
) {
    MultipleChoiceSingleAnswer(
        options = listOf("True", "False"),
        initialSelectedOption = if (isTrue == true) "True" else if (isTrue == false) "False" else null,
        onOptionSelected = { option ->
            onTrueFalseSelected(option == "True")
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DraggableWordCard(text: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(4.dp)
            .background(Color.LightGray)
            .dragAndDropSource {
                detectTapGestures(
                    onLongPress = {
                        Log.d("Drag", "Dragged $text")
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
        Text(text = textState, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun DynamicDragAndDrop(
    optionsA: List<String>,
    optionsB: List<String>,
    //correctAnswers: List<Map<String, String>>,
    //onResults: (Boolean) -> Unit
) {
    val matches = remember { mutableStateOf(mapOf<String, String>()) }
    var resultMessage by remember { mutableStateOf<String?>(null) }

    Column {
        Row {
            Column {
                optionsA.forEach { term ->
                    Text(term, modifier = Modifier.padding(8.dp))
                }
            }
            Column {
                optionsB.forEach { definition ->
                    TargetWordBox(
                        text = matches.value[definition] ?: "Drop here",
                        onDrop = { term ->
                            val newMatches = matches.value.toMutableMap()
                            newMatches[definition] = term
                            matches.value = newMatches
                        }
                    )
                }
            }
            Column {
                optionsB.forEach { term ->
                    DraggableWordCard(term)
                }
            }
        }
    }
}

@Composable
fun FillInTheBlanks(
    question: Question,
    onAnswerSelected: (List<String>) -> Unit
) {
    // Safely cast options to the expected type
    val options = question.options as? List<Map<String, String>> ?: listOf()

    val answers = remember { mutableStateOf<List<String>>(listOf()) }

    Column {
        // Display question description with blanks
        Text(question.questionDescription.replace("____", "__________"))

        // Display options as draggable cards
        options.forEach { optionMap ->
            optionMap["text"]?.let { text ->
                DraggableWordCard(text = text)
            }
        }

        // Correctly count the blanks in the question description
        val blanksCount = "____".toRegex().findAll(question.questionDescription).count()
        val userAnswers = remember { mutableStateListOf<String>().apply { repeat(blanksCount) { add("") } } }

        userAnswers.forEachIndexed { index, _ ->
            TargetWordBox(
                text = userAnswers[index],
                onDrop = { droppedText ->
                    userAnswers[index] = droppedText
                    answers.value = userAnswers.toList()
                }
            )
        }
    }
}

