package eu.tkacas.jslearner.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.ui.theme.PrussianBlue
import eu.tkacas.jslearner.ui.theme.SkyBlue

@Composable
fun TermsCheckboxComponent(
    checkedValue: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTextSelected: (String) -> Unit,
    errorMessageValue: String,
    errorStatus: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checkedValue,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkmarkColor = PrussianBlue,
                    checkedColor = SkyBlue
                )
            )
            PrivacyAndTermsClickableTextComponent(onTextSelected)
        }
        if (errorStatus) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = errorMessageValue,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}