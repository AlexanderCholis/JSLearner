package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.presentation.ui.activity.main.MainActivity
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.presentation.ui.component.PathModuleCard
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExploringPathViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.WelcomeSharedViewModel

@Composable
fun ExploringPathScreen(
    navController: NavController,
    viewModel: ExploringPathViewModel,
    sharedViewModel: WelcomeSharedViewModel
) {
    val experienceLevel = sharedViewModel.experienceLevel.value ?: ExperienceLevel.NO_EXPERIENCE
    val selectedReason = sharedViewModel.selectedReason.value ?: LearningReason.GAIN_SKILLS
    val context = LocalContext.current
    val exploringPathState by viewModel.exploringPathState.collectAsState()

    BackHandler {
        sharedViewModel.setSelectedReason(null)
        navController.navigateUp()
    }

    LaunchedEffect(Unit) {
        viewModel.returnCourses(experienceLevel)
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                color = Color.White,
                onBackClick = {
                    sharedViewModel.setSelectedReason(null)
                    navController.navigateUp()
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
                when (exploringPathState) {
                    is Result.Loading -> {
                        ProgressIndicatorComponent()
                    }

                    is Result.Success -> {
                        val courseList = (exploringPathState as Result.Success).result
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 32.dp, end = 32.dp, bottom = 28.dp)
                        ) {
                            BoldText(text = stringResource(id = R.string.your_path))
                            NormalText(text = stringResource(id = R.string.your_path_description))
                            Spacer(modifier = Modifier.height(8.dp))
                            LazyColumn(
                                modifier = Modifier.weight(1f)
                            ) {
                                items(courseList.size) { index ->
                                    PathModuleCard(
                                        moduleTitleText = courseList[index].title,
                                        moduleDescriptionText = courseList[index].description
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                GeneralButtonComponent(
                                    valueId = R.string.looks_good,
                                    onButtonClicked = {
                                        viewModel.updateUserData(selectedReason, experienceLevel)
                                        val intent =
                                            Intent(context, MainActivity::class.java).apply {
                                                flags =
                                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            }
                                        context.startActivity(intent)
                                    }
                                )
                            }
                        }
                    }

                    is Result.Error -> {
                        Text(
                            text = stringResource(id = R.string.error) + " ${(exploringPathState as Result.Error)}",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    null -> {}
                }
            }
        }
    )
}