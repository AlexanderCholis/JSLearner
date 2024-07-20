package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.presentation.viewmodel.main.LessonViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel

@Composable
fun LessonScreen(
    navController: NavController,
    viewModel: LessonViewModel,
    sharedViewModel: MainSharedViewModel
) {
    val lesson = sharedViewModel.selectedLesson.value
    val previousRoute = navController.previousBackStackEntry?.destination?.route


    if (lesson != null) {
        var currentIndex by rememberSaveable { mutableIntStateOf(if (previousRoute == "startLesson") 0 else lesson.theoriesList.size - 1) }

        val progress by animateFloatAsState(
            targetValue = (currentIndex + 1) / lesson.theoriesList.size.toFloat(), label = ""
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
            }
        ) { innerPadding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 28.dp, end = 28.dp, top = 18.dp, bottom = 28.dp),
                ) {
                    BoldText(text = lesson.title)
                    Spacer(modifier = Modifier.height(10.dp))
                    NormalText(text = lesson.theoriesList[currentIndex])
                    Spacer(modifier = Modifier.weight(1f))
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (lesson.theoriesList.size != 1) {
                            LinearProgressIndicator(progress = progress)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        GeneralButtonComponent(valueId = R.string.next, onButtonClicked = {
                            if (currentIndex < lesson.theoriesList.size - 1) {
                                currentIndex++
                            } else {
                                viewModel.hasQuizForLesson(lesson.id) { hasQuiz ->
                                    if (hasQuiz) {
                                        navController.navigate("startQuiz")
                                    } else {
                                        navController.navigate("roadmap")
                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
    } else {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                BackAppTopBar(
                    color = Color.White,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(innerPadding)
            ) {
                Text(text = stringResource(id = R.string.an_error_occurred))
            }
        }
    }
}