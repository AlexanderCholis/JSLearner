package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.CoursesPathCard

@Composable
fun CoursesPathScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 32.dp, end = 32.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Fundamentals",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 40.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.courses),
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        CoursesPathCard(
            moduleTitleText = "Fundamentals",
            isEnabled = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        CoursesPathCard(
            moduleTitleText = "Fundamentals II",
            isEnabled = false
        )
    }
}

@Preview
@Composable
fun CoursesPathScreenPreview() {
    CoursesPathScreen()
}