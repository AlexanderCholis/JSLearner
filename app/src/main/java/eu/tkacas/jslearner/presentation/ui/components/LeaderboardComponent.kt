package eu.tkacas.jslearner.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = userName,
            modifier = Modifier.weight(1f).padding(start = 8.dp)
        )
        Text(
            text = userScore.toString(),
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
