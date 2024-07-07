package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.Bronze
import eu.tkacas.jslearner.presentation.ui.theme.Gold
import eu.tkacas.jslearner.presentation.ui.theme.Silver
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue


@Composable
fun CircularLeaderComponent(image: Int, borderColor: Color, hasCrown: Boolean = false) {
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
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = R.string.leaderboard_image),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
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
    }
}

@Composable
fun UserScoreBubbleComponent(userScore: Int, leaderType: Int) {
    val backgroundColor = when (leaderType) {
        1 -> Gold // Gold for CircularLeader1Component
        2 -> Silver // Silver for CircularLeader2Component
        3 -> Bronze // Bronze for CircularLeader3Component
        else -> SkyBlue
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = 50.dp, height = 25.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
    ) {
        Text(
            text = userScore.toString(),
            color = Color.White,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun LeaderboardTopSection(image: Int, userScore: Int, leaderType1: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-20).dp)
    ) {
        CircularLeaderComponent(image = image, borderColor = Gold, hasCrown = true)
        UserScoreBubbleComponent(userScore = userScore, leaderType = leaderType1)
    }
}

@Composable
fun LeaderboardRowComponent(image2: Int, score2: Int, image3: Int, score3: Int, leaderType2: Int, leaderType3: Int){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        // CircularLeader2Component and its UserScoreBubbleComponent
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-20).dp)
        ) {
            CircularLeaderComponent(image = image2, borderColor = Silver)
            UserScoreBubbleComponent(userScore = score2, leaderType = leaderType2)
        }

        Spacer(modifier = Modifier.width(25.dp)) // Adjust the spacing between the two Columns as needed

        // CircularLeader3Component and its UserScoreBubbleComponent
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-20).dp)
        ) {
            CircularLeaderComponent(image = image3, borderColor = Bronze)
            UserScoreBubbleComponent(userScore = score3, leaderType = leaderType3)
        }
    }
}

@Preview
@Composable
fun LeaderboardTopSectionDEMO(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-20).dp)
    ) {
        CircularLeaderComponent(image = R.drawable.application, borderColor = Color(0xFFD4AF37), hasCrown = true)
        UserScoreBubbleComponent(userScore = 1000, leaderType = 1)
    }
}