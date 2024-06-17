package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.entity.learningreason.LearningReason
import eu.tkacas.jslearner.domain.entity.learningreason.LearningReasonItem
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.LearningReasonCard
import eu.tkacas.jslearner.presentation.ui.component.NormalText

@Composable
fun LearningReasonScreen(
    navController: NavController
) {
    val selectedReason = remember { mutableStateOf<LearningReason?>(null) }

    val lightBeige = Color(0xFFF8EFE0)

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
            Image(
                painter = painterResource(id = R.drawable.faq),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(color = lightBeige)
            )
            NormalText(textId = R.string.finding_your_path)

            Spacer(modifier = Modifier.height(8.dp))
            BoldText(textId = R.string.why_do_you_want_to_learn)
            Spacer(modifier = Modifier.height(8.dp))
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