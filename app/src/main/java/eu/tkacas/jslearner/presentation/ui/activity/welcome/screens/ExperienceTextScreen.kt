package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExperienceTextViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.WelcomeSharedViewModel

@Composable
fun ExperienceTextScreen(
    navController: NavController,
    viewModel: ExperienceTextViewModel,
    sharedViewModel: WelcomeSharedViewModel
) {
    val experienceLevel = sharedViewModel.experienceLevel.value ?: ExperienceLevel.NO_EXPERIENCE
    val texts = viewModel.returnTexts(experienceLevel)
    val previousRoute = navController.previousBackStackEntry?.destination?.route
    var currentIndex by rememberSaveable { mutableIntStateOf(if (previousRoute == "experienceLevel") 0 else texts.size - 1) }

    val progress by animateFloatAsState(
        targetValue = (currentIndex + 1) / texts.size.toFloat(), label = ""
    )

    Scaffold(
        modifier = Modifier
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
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 28.dp, end = 28.dp, bottom = 28.dp)
                ) {
                    BoldText(text = stringResource(id = texts[currentIndex].boldText))
                    Spacer(modifier = Modifier.height(10.dp))
                    NormalText(text = stringResource(id = texts[currentIndex].normalText))
                    Spacer(modifier = Modifier.height(20.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = texts[currentIndex].image),
                        contentDescription = stringResource(id = R.string.simple_image)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LinearProgressIndicator(progress = progress)
                        Spacer(modifier = Modifier.height(20.dp))
                        GeneralButtonComponent(valueId = R.string.next, onButtonClicked = {
                            if (currentIndex < texts.size - 1) {
                                currentIndex++
                            } else {
                                navController.navigate("learningReason")
                            }
                        })
                    }
                }
            }
        }
    )
}