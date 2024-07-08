package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.Bronze
import eu.tkacas.jslearner.presentation.ui.theme.Gold
import eu.tkacas.jslearner.presentation.ui.theme.Silver
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue


@Composable
fun CircularLeaderComponent(image: Int, borderColor: Color, hasCrown: Boolean = false, userScore: Int, leaderType: Int, modifier: Modifier = Modifier) {
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
        userScore?.let { score ->
            leaderType?.let { type ->
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


