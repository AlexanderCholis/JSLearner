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
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue


@Composable
fun CircularLeader1Component(image: Int) {
    Box(
        modifier = Modifier
            .size(126.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp) //Size of Circle
                .offset(x = 12.dp, y = 22.dp)
                .clip(CircleShape) //Cut into circle shape
                .border(4.dp, Color(0xFFD4AF37), CircleShape) //Gold outline
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Image(
            painter = painterResource(R.drawable.crown),
            contentDescription = "Crown",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(30.dp)
                .offset(y = (+1).dp)
        )
    }
}

@Composable
fun CircularLeader2Component(image: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(4.dp, Color(0xFFA9A9A9), CircleShape)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun CircularLeader3Component(image: Int, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(4.dp, Color(0xFFCD7F32), CircleShape)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun UserScoreBubbleComponent(userScore: Int, leaderType: Int) {
    val backgroundColor = when (leaderType) {
        1 -> Color(0xFFD4AF37) // Gold for CircularLeader1Component
        2 -> Color(0xFFA9A9A9) // Silver for CircularLeader2Component
        3 -> Color(0xFFCD7F32) // Bronze for CircularLeader3Component
        else -> SkyBlue // Default color
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
        CircularLeader1Component(image = image)
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
            CircularLeader2Component(image = image2)
            UserScoreBubbleComponent(userScore = score2, leaderType = leaderType2)
        }

        Spacer(modifier = Modifier.width(25.dp)) // Adjust the spacing between the two Columns as needed

        // CircularLeader3Component and its UserScoreBubbleComponent
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-20).dp)
        ) {
            CircularLeader3Component(image = image3)
            UserScoreBubbleComponent(userScore = score3, leaderType = leaderType3)
        }
    }
}

@Preview
@Composable
fun LeaderboardTopSectionDEMO(){//image: Int, score: Int, lead) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-20).dp)
    ) {
        CircularLeader1Component(image = R.drawable.application)
        UserScoreBubbleComponent(userScore = 1000, leaderType = 1)
    }
}