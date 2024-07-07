package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.Bronze
import eu.tkacas.jslearner.presentation.ui.theme.Gold
import eu.tkacas.jslearner.presentation.ui.theme.Silver
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue


@Composable
fun LeaderboardComponent(userImage: Int, userName: String, userScore: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
            .background(SkyBlue)
            .padding(16.dp)
            .width(350.dp)
    ) {
        Image(
            painter = painterResource(id = userImage),
            contentDescription = stringResource(id = R.string.leaderboard_image),
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = userName,
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
        Text(
            text = userScore.toString(),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun WinnersPodiumComponent() {
    Canvas(modifier = Modifier.fillMaxWidth().height(100.dp)) {
        val podiumWidth = size.width / 5
        val podiumHeight = size.height / 3
        val paint = android.graphics.Paint().apply {
            color = android.graphics.Color.BLACK
            textSize = 40f
            textAlign = android.graphics.Paint.Align.CENTER
        }

        // First place podium
        val firstPlaceTopLeft = Offset((size.width / 2) - (podiumWidth / 2), size.height - (podiumHeight * 1.75f))
        drawRect(
            color = Gold,
            topLeft = firstPlaceTopLeft,
            size = Size(podiumWidth, podiumHeight * 1.75f)
        )
        drawContext.canvas.nativeCanvas.drawText(
            "1st",
            firstPlaceTopLeft.x + podiumWidth / 2,
            firstPlaceTopLeft.y + podiumHeight * 1.75f / 2 + paint.textSize / 2,
            paint
        )

        // Second place podium
        val secondPlaceTopLeft = Offset((size.width / 2) - (podiumWidth * 1.5f), size.height - podiumHeight * 1.25f)
        drawRect(
            color = Silver,
            topLeft = secondPlaceTopLeft,
            size = Size(podiumWidth, podiumHeight*1.25f)
        )
        drawContext.canvas.nativeCanvas.drawText(
            "2nd",
            secondPlaceTopLeft.x + podiumWidth / 2,
            secondPlaceTopLeft.y + podiumHeight * 1.25f / 2 + paint.textSize / 2,
            paint
        )

        // Third place podium
        val thirdPlaceTopLeft = Offset((size.width / 2) + (podiumWidth / 2), size.height - podiumHeight)
        drawRect(
            color = Bronze,
            topLeft = thirdPlaceTopLeft,
            size = Size(podiumWidth, podiumHeight)
        )
        drawContext.canvas.nativeCanvas.drawText(
            "3rd",
            thirdPlaceTopLeft.x + podiumWidth / 2,
            thirdPlaceTopLeft.y + podiumHeight / 2 + paint.textSize / 2,
            paint
        )
    }
}

