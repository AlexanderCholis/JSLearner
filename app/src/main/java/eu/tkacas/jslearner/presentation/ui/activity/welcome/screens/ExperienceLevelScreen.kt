package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevelItem
import eu.tkacas.jslearner.presentation.ui.component.ExperienceLevelCard

@Composable
fun ExperienceLevelScreen() {
    val levels = listOf(
        ExperienceLevelItem(R.drawable.star_1, R.string.no_experience, ExperienceLevel.NO_EXPERIENCE),
        ExperienceLevelItem(R.drawable.stars_2, R.string.some_experience, ExperienceLevel.SOME_EXPERIENCE),
        ExperienceLevelItem(R.drawable.stars_3, R.string.a_lot_of_experience, ExperienceLevel.A_LOT_OF_EXPERIENCE)
    )

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 32.dp, end = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.experience_level_until_now),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            levels.forEachIndexed { _, item ->
                Spacer(modifier = Modifier.height(16.dp))
                ExperienceLevelCard(
                    image = item.image,
                    text = item.text,
                    onClick = {
                        // TODO navigate to correct screen based on the selected experience level
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ExperienceLevelScreenPreview() {
    ExperienceLevelScreen()
}