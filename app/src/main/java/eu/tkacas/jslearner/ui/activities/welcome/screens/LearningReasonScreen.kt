package eu.tkacas.jslearner.ui.activities.welcome.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.ui.components.LearningReasonCard

@Composable
fun LearningReasonScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.finding_your_path),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 40.dp)
        )
        Text(
            text = stringResource(id = R.string.why_do_you_want_to_learn),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        LearningReasonCard(
            image = R.drawable.career,
            text = R.string.gain_skills
        )
        Spacer(modifier = Modifier.height(16.dp))
        LearningReasonCard(
            image = R.drawable.work,
            text = R.string.change_career
        )
        Spacer(modifier = Modifier.height(16.dp))
        LearningReasonCard(
            image = R.drawable.`fun`,
            text = R.string.just_for_fun
        )
        Spacer(modifier = Modifier.height(16.dp))
        LearningReasonCard(
            image = R.drawable.games,
            text = R.string.develop_games
        )
        Spacer(modifier = Modifier.height(16.dp))
        LearningReasonCard(
            image = R.drawable.application,
            text = R.string.build_an_application_or_website
        )
    }
}

@Preview
@Composable
fun LearningReasonScreenPreview() {
    LearningReasonScreen()
}