package eu.tkacas.jslearner.ui.activities.welcome.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R



@Composable
fun WelcomeScreen() {
    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.application),
            contentDescription = "Welcome Image"
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = androidx.compose.ui.Modifier.height(60.dp))
        Text(
            text = stringResource(id = R.string.creators),
            textAlign = TextAlign.Center
        )
    }



}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}