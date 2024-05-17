package eu.tkacas.jslearner.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import eu.tkacas.jslearner.ui.theme.SkyBlue
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import eu.tkacas.jslearner.R
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import eu.tkacas.jslearner.ui.theme.PrussianBlue


@Composable
fun ScoreComponent(userScore: Int) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .size(width = 200.dp, height = 80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(SkyBlue)
                .padding(7.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = userScore.toString(), //up to 4 digits no problem
                    modifier = Modifier.padding(start = 2.dp),
                    fontSize = 40.sp
                )
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.trophy),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
}


@Composable
fun ScoreProgressBarComponent(userScore: Int, maxScore: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(8.dp)
    ) {
        LinearProgressIndicator(
            progress = userScore / maxScore,
            color = PrussianBlue,
            modifier = Modifier
                .weight(8f)
                .padding(end = 16.dp)
                .clip(RoundedCornerShape(35.dp))
        )
        Text(
            text = "${(userScore / maxScore * 100).toInt()}%",
            modifier = Modifier.weight(2f)
        )
    }
}

