package eu.tkacas.jslearner.presentation.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue
import eu.tkacas.jslearner.presentation.ui.theme.componentShapes

@Composable
fun AuthTextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    painterResource: Painter,
    contentDescription: String,
    keyboardType: KeyboardType,
    supportedTextValue: String,
    errorStatus: Boolean = false
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(if (isLandscape) 0.3f else 1f) // custom weights depending on the orientation
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = keyboardType
        ),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource,
                contentDescription = contentDescription
            )
        },
        supportingText = {
            if (errorStatus) {
                Text(
                    text = supportedTextValue,
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                Text(
                    text = ""
                )
            }
        },
        isError = errorStatus
    )
}

@Composable
fun PasswordTextFieldComponent(
    value: String,
    onValueChange: (String) -> Unit,
    labelValue: String,
    supportedTextValue: String,
    errorStatus: Boolean = false
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(if (isLandscape) 0.3f else 1f) //custom weights depending on the orientation
            .clip(componentShapes.small),
        label = { Text(text = labelValue) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.password),
                contentDescription = stringResource(id = R.string.password_hint)
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                painterResource(id = R.drawable.visibility)
            } else {
                painterResource(id = R.drawable.visibility_off)
            }

            val contentDescription = if (passwordVisible.value) {
                stringResource(id = R.string.hide_password)
            } else {
                stringResource(id = R.string.show_password)
            }

            IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                Icon(
                    painter = iconImage,
                    contentDescription = contentDescription
                )
            }
        },
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        supportingText = {
            if (errorStatus) {
                Text(
                    text = supportedTextValue,
                    color = MaterialTheme.colorScheme.error
                )
            } else {
                Text(
                    text = ""
                )
            }
        },
        isError = errorStatus
    )
}

@Composable
fun NameFieldComponent(
    firstName: String,
    lastName: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        UserInitialsCircle(firstName = firstName, lastName = lastName)

        Spacer(modifier = Modifier.width(20.dp))

        androidx.compose.material.Text(
            text = firstName,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            color = PrussianBlue
        )

        Spacer(modifier = Modifier.width(8.dp))

        androidx.compose.material.Text(
            text = lastName,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            color = SkyBlue
        )
    }
}