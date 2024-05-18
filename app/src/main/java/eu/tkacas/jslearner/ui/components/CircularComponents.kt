package eu.tkacas.jslearner.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R


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
fun CircularLeader2Component(image: Int) {
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
fun CircularLeader3Component(image: Int) {
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



