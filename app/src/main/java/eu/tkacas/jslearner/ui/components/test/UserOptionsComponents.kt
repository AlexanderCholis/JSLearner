package eu.tkacas.jslearner.ui.components.test

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.ui.theme.PrussianBlue
import eu.tkacas.jslearner.ui.theme.SkyBlue
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

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

@Composable
fun SequenceHotspot(
    items: List<String>,
    sequence: List<Int>,
    userSequence: List<Int>,
    onSequenceChanged: (Int, Int) -> Unit
) {
    Column {
        items.forEachIndexed { index, item ->
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }

            Box(
                modifier = Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            offsetY += dragAmount.y
                            val newIndex = ((index + offsetX).roundToInt()).coerceIn(0, items.size - 1)
                            onSequenceChanged(index, newIndex)
                        }
                    }
            ) {
                Text(text = item)
            }
        }
    }
}

@Composable
fun DragTheWords(
    words: List<String>,
    targetWords: List<String>,
    userAnswers: List<String>,
    onWordDropped: (Int, String) -> Unit
) {
    Column {
        Column{
            words.forEach { word ->
                var offsetX by remember { mutableFloatStateOf(0f) }
                var offsetY by remember { mutableFloatStateOf(0f) }

                Box(
                    modifier = Modifier
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume() // Consume the gesture so it doesn't propagate to other views
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                                val newIndex = ((words.indexOf(word) + offsetX).roundToInt()).coerceIn(0, words.size - 1)
                                onWordDropped(newIndex, word)
                            }
                        }
                ) {
                    Text(text = word)
                }
            }
        }
        Column {
            targetWords.forEachIndexed { index, targetWord ->
                Box(
                    modifier = Modifier.background(Color.LightGray)
                ) {
                    Text(text = userAnswers[index])
                }
            }
        }

    }
}
