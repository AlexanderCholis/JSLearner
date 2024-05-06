package eu.tkacas.jslearner.ui.activities.welcome.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.ui.components.ExperienceLevelCard

@Composable
fun ExperienceLevelScreen() {
    Column(
        modifier= Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "How much experience do you have with coding so far?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        ExperienceLevelCard(
            icon = R.drawable.star,
            text = "No experience"
        )
        Spacer(modifier = Modifier.height(16.dp))
        ExperienceLevelCard(
            icon = R.drawable.star,
            text = "Some experience"
        )
        Spacer(modifier = Modifier.height(16.dp))
        ExperienceLevelCard(
            icon = R.drawable.star,
            text = "A lot of experience"
        )
    }
}

@Preview
@Composable
fun ExperienceLevelScreenPreview() {
    ExperienceLevelScreen()
}