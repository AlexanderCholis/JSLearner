package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

@Composable
fun FloatingButton(
    onButtonClicked: () -> Unit
) {
    FloatingActionButton(
        containerColor = SkyBlue,
        onClick = onButtonClicked
    ) {
        Icon(
            painter = painterResource(id = R.drawable.list),
            contentDescription = stringResource(id = R.string.courses_list)
        )
    }
}