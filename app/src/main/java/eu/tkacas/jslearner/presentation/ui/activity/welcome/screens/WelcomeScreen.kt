package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 28.dp, start = 32.dp, end = 32.dp, bottom = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            item {
                Image(
                    painter = painterResource(id = R.drawable.jslearner_transparent),
                    contentDescription = stringResource(id = R.string.welcome_image)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.welcome_text),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = stringResource(id = R.string.creators),
                    textAlign = TextAlign.Center
                )
                //Spacer(modifier = Modifier.height(250.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        GeneralButtonComponent(
                            valueId = R.string.login,
                            onButtonClicked = {
                                navController.navigate("login")
                            }
                        )
                    }
                }
            }
        }
    }
}