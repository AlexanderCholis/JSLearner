package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.domain.model.PodiumUser
import eu.tkacas.jslearner.presentation.ui.theme.Bronze
import eu.tkacas.jslearner.presentation.ui.theme.Gold
import eu.tkacas.jslearner.presentation.ui.theme.Silver


@Composable
fun WinnersPodiumComponentWithLeaders(
    podiumUserList: List<PodiumUser>
) {
    val sortedPodiumUsers = podiumUserList.sortedBy { it.position }
    val (firstname1, lastname1, userScore1) = sortedPodiumUsers.getOrNull(0)?.let { Triple(it.firstName, it.lastName, it.score) }
        ?: Triple("", "", 0)
    val (firstname2, lastname2, userScore2) = sortedPodiumUsers.getOrNull(1)?.let { Triple(it.firstName, it.lastName, it.score) }
        ?: Triple("", "", 0)
    val (firstname3, lastname3, userScore3) = sortedPodiumUsers.getOrNull(2)?.let { Triple(it.firstName, it.lastName, it.score) }
        ?: Triple("", "", 0)

    Box(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        // First place podium
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(126.dp)
                        .offset(y = 30.dp)
                ) {
                    CircularLeaderComponent(
                        firstName = firstname1,
                        lastName = lastname1,
                        borderColor = Gold,
                        hasCrown = true,
                        userScore = userScore1,
                        leaderType = 1
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    val podiumWidth1 = size.width / 3
                    val podiumHeight1 = size.height / 3
                    val paint = android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 40f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                    val firstPlaceTopLeft =
                        Offset(
                            (size.width / 2) - (podiumWidth1 / 2),
                            size.height - (podiumHeight1 * 1.95f)
                        )
                    drawRect(
                        color = Gold,
                        topLeft = firstPlaceTopLeft,
                        size = Size(podiumWidth1, podiumHeight1 * 1.95f)
                    )
                    drawContext.canvas.nativeCanvas.drawText(
                        "1st",
                        firstPlaceTopLeft.x + podiumWidth1 / 2,
                        firstPlaceTopLeft.y + podiumHeight1 * 1.75f / 2 + paint.textSize / 2,
                        paint
                    )
                }
            }
        }

        // Second place podium
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = 36.dp)
        ) {
            Column() {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = 15.dp, y = 40.dp)
                ) {
                    CircularLeaderComponent(
                        firstName = firstname2,
                        lastName = lastname2,
                        borderColor = Silver,
                        hasCrown = false,
                        userScore = userScore2,
                        leaderType = 2
                    )
                }
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    val podiumWidth2 = size.width / 3
                    val podiumHeight2 = size.height / 3
                    val paint = android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 40f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                    val secondPlaceTopLeft = Offset(
                        (size.width / 2) - (podiumWidth2 * 1.5f),
                        size.height - podiumHeight2 * 1.25f
                    )
                    drawRect(
                        color = Silver,
                        topLeft = secondPlaceTopLeft,
                        size = Size(podiumWidth2, podiumHeight2 * 1.25f)
                    )
                    drawContext.canvas.nativeCanvas.drawText(
                        "2nd",
                        secondPlaceTopLeft.x + podiumWidth2 / 2,
                        secondPlaceTopLeft.y + podiumHeight2 * 1.25f / 2 + paint.textSize / 2,
                        paint
                    )
                }
            }
        }


        // Third place podium
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 0.dp, y = 36.dp)
        ) {
            Column() {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset(x = 265.dp, y = 50.dp)
                ) {
                    CircularLeaderComponent(
                        firstName = firstname3,
                        lastName = lastname3,
                        borderColor = Bronze,
                        hasCrown = false,
                        userScore = userScore3,
                        leaderType = 3
                    )
                }
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    val podiumWidth3 = size.width / 3
                    val podiumHeight3 = size.height / 3
                    val paint = android.graphics.Paint().apply {
                        color = android.graphics.Color.BLACK
                        textSize = 40f
                        textAlign = android.graphics.Paint.Align.CENTER
                    }
                    val thirdPlaceTopLeft =
                        Offset((size.width / 2) + (podiumWidth3 / 2), size.height - podiumHeight3)
                    drawRect(
                        color = Bronze,
                        topLeft = thirdPlaceTopLeft,
                        size = Size(podiumWidth3, podiumHeight3)
                    )
                    drawContext.canvas.nativeCanvas.drawText(
                        "3rd",
                        thirdPlaceTopLeft.x + podiumWidth3 / 2,
                        thirdPlaceTopLeft.y + podiumHeight3 / 2 + paint.textSize / 2,
                        paint
                    )
                }
            }
        }
    }
}

