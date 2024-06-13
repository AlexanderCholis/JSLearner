package eu.tkacas.jslearner.presentation.ui.components.test

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
import androidx.compose.runtime.setValue

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

//@Composable
//fun SequenceHotspot(
//    items: List<String>,
//    sequence: List<Int>,
//    userSequence: List<Int>,
//    onSequenceChanged: (Int, Int) -> Unit
//) {
//    Column {
//        items.forEachIndexed { index, item ->
//            var offsetX by remember { mutableFloatStateOf(0f) }
//            var offsetY by remember { mutableFloatStateOf(0f) }
//
//            Box(
//                modifier = Modifier
//                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
//                    .pointerInput(Unit) {
//                        detectDragGestures { change, dragAmount ->
//                            change.consume()
//                            offsetX += dragAmount.x
//                            offsetY += dragAmount.y
//                            val newIndex =
//                                ((index + offsetX).roundToInt()).coerceIn(0, items.size - 1)
//                            onSequenceChanged(index, newIndex)
//                        }
//                    }
//            ) {
//                Text(text = item)
//            }
//        }
//    }
//}
//@Composable
//fun DragTheWords(
//    words: List<String>,
//    targetWords: List<String>,
//    userAnswers: List<String>,
//    onWordDropped: (Int, String) -> Unit
//) {
//    val droppedWords = remember { mutableStateOf(List(words.size) { "Drop Me here" }) }
//    val offsets = remember { words.map { mutableStateOf(Offset.Zero) } }
//    var boxHeight by remember { mutableStateOf(0f) }
//
//    // State hoisting for better composability
//    var draggingIndex by remember { mutableStateOf<Int?>(null) }
//
//    Row(Modifier.fillMaxWidth()) {
//        Column(Modifier.weight(1f)) {
//            targetWords.forEachIndexed { index, _ ->
//                TargetWordBox(text = userAnswers[index])
//            }
//        }
//
//        Column(Modifier.weight(1f)) {
//            droppedWords.value.forEachIndexed { index, word ->
//                DroppedWordBox(
//                    text = word,
//                    onHeightMeasured = { height ->
//                        boxHeight = height.toFloat()
//                        println("Box height: $boxHeight")
//                    }
//                )
//            }
//        }
//
//        Column(Modifier.weight(1f)) {
//            words.forEachIndexed { index, word ->
//                val offset by offsets[index]
//
//                Box(
//                    modifier = Modifier
//                        .onGloballyPositioned { coordinates ->
//                            boxHeight = coordinates.size.height.toFloat()
//                        }
//                        .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
//                        .pointerInput(Unit) {
//                            detectDragGestures(
//                                onDragStart = {
//                                    draggingIndex = index
//                                },
//                                onDrag = { change, dragAmount ->
//                                    change.consume()
//                                    offsets[index].value += dragAmount
//                                },
//                                onDragEnd = {
//                                    if (draggingIndex == index) {
//                                        val newIndex = calculateDropIndex(offset.y, boxHeight, words.size)
//                                        onWordDropped(newIndex, word)
//                                        droppedWords.value = droppedWords.value.toMutableList().also { it[newIndex] = word }
//                                        println("Dropped word: $word")
//                                        println("New index: $newIndex")
//                                        println("Dropped words: $droppedWords")
//                                        offsets[index].value = Offset.Zero
//                                    }
//                                    draggingIndex = null
//                                }
//                            )
//                        }
//                ) {
//                    DraggableWordCard(text = word, isDragging = draggingIndex == index)
//                }
//            }
//        }
//    }
//}
//
//fun calculateDropIndex(yOffset: Float, boxHeight: Float, wordsSize: Int): Int {
//    // Calculate drop index based on the position relative to the top edges of the drop areas
//    val approximateIndex = (yOffset / boxHeight).toInt()
//    return if (approximateIndex < 0) 0 else if (approximateIndex >= wordsSize) wordsSize - 1 else approximateIndex
//}
//
//@Composable
//fun TargetWordBox(text: String) {
//    Surface(shape = RoundedCornerShape(8.dp), color = MaterialTheme.colorScheme.inversePrimary, modifier = Modifier.padding(4.dp)) {
//        Text(text = text, modifier = Modifier.padding(8.dp))
//    }
//}
//
//@Composable
//fun DroppedWordBox(text: String = "Drop Here", onHeightMeasured: (Int) -> Unit) {
//    val heightState = remember { mutableStateOf(0) } //
//
//    Card(
//        shape = RoundedCornerShape(8.dp),
//        border = BorderStroke(2.dp, Color.Black),
//        modifier = Modifier
//            .padding(4.dp)
//            .height(37.dp)
//            .onGloballyPositioned {
//                heightState.value = it.size.height
//                onHeightMeasured(it.size.height)
//                println("Height measured: ${it.size.height}")
//            }
//    ) {
//        Text(text = text, modifier = Modifier.padding(8.dp))
//    }
//}
//
//@Composable
//fun DraggableWordCard(text: String, isDragging: Boolean) {
//    Card(
//        shape = RoundedCornerShape(8.dp),
//        modifier = Modifier
//            .padding(4.dp)
//            .background(if (isDragging) Color.LightGray else Color.White)
//            .shadow(if (isDragging) 8.dp else 0.dp), // Add shadow when dragging
//    ) {
//        Text(text = text, modifier = Modifier.padding(8.dp))
//    }
//}
//
//@Preview
//@Composable
//fun DragTheWordsPreview() {
//    DragTheWords(
//        words = listOf("Word 1", "Word 2", "Word 3"),
//        targetWords = listOf("Target 1", "Target 2", "Target 3"),
//        userAnswers = listOf("Answer 1", "Answer 2", "Answer 3"),
//        onWordDropped = { index, word -> println("Word $word dropped at index $index") }
//    )
//}
//
// To be fixed
// .onGloballyPositioned { layoutCoordinates ->
//                val position = layoutCoordinates.positionInWindow()
//                Log.d("DroppedWordBox", "Position: ${position}")
//                onHeightMeasured(position.y.toInt())
//            }