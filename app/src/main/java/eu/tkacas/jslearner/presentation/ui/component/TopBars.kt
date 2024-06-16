package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackAppTopBar(
    title: String,
    color: Color,
    onBackClick: () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onBackClick()
                },
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = stringResource(id = R.string.go_back)
                    )
                }
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = color
        )
    )
}