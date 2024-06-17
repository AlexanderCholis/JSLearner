package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import eu.tkacas.jslearner.domain.entity.experience.ExperienceLevel
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExperienceTextViewModel

@Composable
fun ExperienceTextScreen(
    navController: NavController,
    experienceLevel: String
) {
    val viewModel = viewModel<ExperienceTextViewModel>()
    val texts = viewModel.returnTexts(ExperienceLevel.valueOf(experienceLevel))
    val previousRoute = navController.previousBackStackEntry?.destination?.route
    var currentIndex by rememberSaveable { mutableStateOf(if (previousRoute == "experienceLevel") 0 else texts.size - 1) }

    Scaffold(
        modifier= Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                color = Color.White,
                onBackClick = {
                    if (currentIndex > 0) {
                        currentIndex--
                    } else {
                        navController.navigateUp()
                    }
                }
            )
        },
        content = { padding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 80.dp)
            ) {
                Column {
                    Text(text = stringResource(id = texts[currentIndex].boldText))
                    Text(text = stringResource(id = texts[currentIndex].normalText))
                    Button(onClick = {
                        if (currentIndex < texts.size - 1) {
                            currentIndex++
                        } else {
                            navController.navigate("learningReason")
                        }
                    }) {
                        Text("Next")
                    }
                }
            }
        }
    )
}