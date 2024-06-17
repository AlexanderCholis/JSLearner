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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.domain.model.learningreason.LearningReasonItem
import eu.tkacas.jslearner.presentation.ui.component.LearningReasonCard

@Composable
fun LearningReasonScreen() {
    val selectedReason = remember { mutableStateOf<LearningReason?>(null) }

    val reasons = listOf(
        LearningReasonItem(R.drawable.career, R.string.gain_skills, LearningReason.GAIN_SKILLS),
        LearningReasonItem(R.drawable.work, R.string.change_career, LearningReason.CHANGE_CAREER),
        LearningReasonItem(R.drawable.`fun`, R.string.just_for_fun, LearningReason.JUST_FOR_FUN),
        LearningReasonItem(R.drawable.games, R.string.develop_games, LearningReason.DEVELOP_GAMES),
        LearningReasonItem(R.drawable.application, R.string.build_an_application_or_website, LearningReason.BUILD_APP_OR_WEBSITE)
    )

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp, end = 32.dp),
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
            reasons.forEachIndexed { index, item ->
                LearningReasonCard(
                    image = item.image,
                    text = item.text,
                    isSelected = selectedReason.value == item.reason,
                    onSelected = { selectedReason.value = item.reason }
                )
                if (index < reasons.size - 1) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun LearningReasonScreenPreview() {
    LearningReasonScreen()
}