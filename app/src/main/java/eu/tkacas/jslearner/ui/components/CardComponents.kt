package eu.tkacas.jslearner.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.ui.theme.PrussianBlue
import eu.tkacas.jslearner.ui.theme.SkyBlue

@Composable
fun ExperienceLevelCard(
    image : Int,
    text : Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = cardColors(containerColor = Color.White),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(min = 120.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(end = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = stringResource(id = text),
                    modifier = Modifier
                        .size(110.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(0.6f)
            ) {
                Text(
                    text = stringResource(id = text),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun LearningReasonCard(
    image : Int,
    text : Int,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val cardColor = if (isSelected) SkyBlue else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onSelected),
        colors = cardColors(containerColor = cardColor),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = text),
                modifier = Modifier
                    .size(48.dp)
            )
            Text(
                text = stringResource(id = text),
                modifier = Modifier
                    .padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun PathModuleCard(
    moduleTitleText : String,
    moduleDescriptionText : String,
    isExpanded: MutableState<Boolean>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = cardColors(containerColor = Color.White),
        elevation = cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = moduleTitleText,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                IconButton(
                    onClick = { isExpanded.value = !isExpanded.value }
                ) {
                    Image(
                        painter = if(isExpanded.value) painterResource(id = R.drawable.arrow_up) else painterResource(id = R.drawable.arrow_down),
                        contentDescription = null
                    )
                }
            }
            if(isExpanded.value) {
                Row(
                    modifier = Modifier
                        .padding(bottom = 6.dp, start = 4.dp)
                ) {
                    Text(
                        text = moduleDescriptionText
                    )
                }
            }
        }
    }
}