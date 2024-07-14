package eu.tkacas.jslearner.presentation.ui.component.quiz

import android.annotation.SuppressLint
import android.content.ClipData
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
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
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI
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
    question: QuestionUI,
    selectedOptions: List<String>?,
    onOptionSelected: (String, Boolean) -> Unit
) {
    val options = question.options as List<String>

    val safeSelectedOptions = selectedOptions ?: emptySet()
    Column {
        options.forEach { option ->
            val key = "${question.questionDescription.hashCode()}$option"
            val isSelected = remember(key) { mutableStateOf(option in safeSelectedOptions) }

            MultipleChoiceMultipleCard(
                text = option,
                isSelected = isSelected,
                onSelected = {
                    isSelected.value = !isSelected.value
                    onOptionSelected(option, isSelected.value)
                }
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
                    event
                        .mimeTypes()
                        .contains("text/plain")
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
fun FillInTheBlanks(
    question: QuestionUI,
    onAnswerSelected: (List<String>) -> Unit
) {
    val options = question.options as List<String>
    val parts = question.questionDescription.split("____")
    val answers = remember { mutableStateListOf<String>().apply { repeat(parts.size - 1) { add("") } } }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.height(35.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.your_answer_is),
                style = TextStyle(fontSize = 16.sp)
            )
            parts.indices.forEach { index ->
                if (index < parts.size - 1) {
                    TargetWordBox(
                        text = answers[index],
                        onDrop = { droppedText ->
                            answers[index] = droppedText
                            onAnswerSelected(answers.toList())
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceEvenly
        ) {
            // Display options as draggable cards
            options.forEach { option ->
                DraggableWordCard(text = option)
            }
        }
    }
}