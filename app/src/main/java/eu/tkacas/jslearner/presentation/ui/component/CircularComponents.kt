package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.presentation.ui.theme.Bronze
import eu.tkacas.jslearner.presentation.ui.theme.Gold
import eu.tkacas.jslearner.presentation.ui.theme.LightBeige
import eu.tkacas.jslearner.presentation.ui.theme.PrussianBlue
import eu.tkacas.jslearner.presentation.ui.theme.Silver
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue


@Composable
fun CircularLeaderComponent(
    firstName: String,
    lastName: String,
    borderColor: Color,
    hasCrown: Boolean = false,
    userScore: Int,
    leaderType: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(if (hasCrown) 126.dp else 100.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(x = if (hasCrown) 12.dp else 0.dp, y = if (hasCrown) 22.dp else 0.dp)
                .clip(CircleShape)
                .border(4.dp, borderColor, CircleShape)
                .background(Color.White, CircleShape)
        ) {
            UserInitialsCircle(firstName = firstName, lastName = lastName, backgroundColor = LightBeige, textColor = PrussianBlue, size = 100.dp, fontSize = 40.sp)
        }
        if (hasCrown) {
            Image(
                painter = painterResource(R.drawable.crown),
                contentDescription = stringResource(id = R.string.crown),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(30.dp)
                    .offset(y = 1.dp)
            )
        }
        userScore.let { score ->
            leaderType.let { type ->
                val backgroundColor = when (type) {
                    1 -> Gold
                    2 -> Silver
                    3 -> Bronze
                    else -> SkyBlue
                }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(width = 50.dp, height = 20.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(backgroundColor)
                        .align(Alignment.BottomCenter)
                ) {
                    Text(
                        text = score.toString(),
                        color = Color.White,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
fun UserInitialsCircle(
    firstName: String,
    lastName: String,
    backgroundColor: Color = PrussianBlue,
    textColor: Color = Color.White,
    size: Dp = 65.dp,
    fontSize: TextUnit = 24.sp
) {
    val initials = "${firstName.firstOrNull()?.uppercaseChar() ?: ""}${lastName.firstOrNull()?.uppercaseChar() ?: ""}"

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .background(color = backgroundColor, shape = CircleShape)
    ) {
        Text(
            text = initials,
            color = textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ExperienceLevelComponent(user: User?) {
    val experienceText = when (user?.experienceLevel) {
        ExperienceLevel.SOME_EXPERIENCE -> "Intermediate"
        ExperienceLevel.NO_EXPERIENCE -> "Beginner"
        ExperienceLevel.A_LOT_OF_EXPERIENCE -> "Expert"
        else -> "Unknown"
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(SkyBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Experience Level",
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = experienceText,
            modifier = Modifier.padding(top = 8.dp),
            fontSize = 14.sp,
            color = PrussianBlue
        )
    }
}


