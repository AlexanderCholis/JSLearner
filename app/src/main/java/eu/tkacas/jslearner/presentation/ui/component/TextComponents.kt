package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.GrayColor
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue

@Composable
fun AuthHeadingTextComponent(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = colorResource(id = R.color.colorText),
        textAlign = TextAlign.Center
    )
}

@Composable
fun GeneralButtonTextComponent(value: String) {
    Text(
        text = value,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ),
        color = Color.White
    )
}

@Composable
fun AuthDividerTextComponent(value: String) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = value,
        style = TextStyle(
            fontSize = 18.sp
        ),
        color = GrayColor
    )
}

@Composable
fun NormalText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textAlign = TextAlign.Justify
    )
}

@Composable
fun BoldText(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun ErrorMessageText(errorMessage: String?) {
    Text(
        text = errorMessage ?: "",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.error
    )
}

@Composable
fun CenteredAboutText() {
    val context = LocalContext.current
    val about = stringResource(id = R.string.jslearner_welcome_description, context)

    androidx.compose.material.Text(
        text = about,
        style = androidx.compose.material.MaterialTheme.typography.body1,
        textAlign = TextAlign.Left,
        color = PrussianBlue,
        modifier = Modifier.fillMaxWidth()
    )
}