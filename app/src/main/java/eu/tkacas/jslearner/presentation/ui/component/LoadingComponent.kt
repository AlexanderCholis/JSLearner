package eu.tkacas.jslearner.presentation.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

@Composable
@Preview
fun ProgressIndicatorComponent(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(20.dp)
    ){
        CircularProgressIndicator(
            modifier = Modifier.size(150.dp),
            color = SkyBlue,
            strokeWidth = 10.dp
        )
    }
}