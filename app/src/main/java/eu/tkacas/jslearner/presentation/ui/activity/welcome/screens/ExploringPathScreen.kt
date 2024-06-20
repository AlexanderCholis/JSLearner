package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.presentation.ui.activity.main.MainActivity
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.presentation.ui.component.PathModuleCard
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExploringPathViewModel

@Composable
fun ExploringPathScreen(
    navController: NavController,
    viewModel: ExploringPathViewModel,
    experienceLevel: ExperienceLevel,
    selectedReason: LearningReason
) {
    val context = LocalContext.current

    Scaffold(
        modifier= Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                color = Color.White,
                onBackClick = {
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
                    .padding(top = 30.dp, bottom = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp, start = 32.dp, end = 32.dp),
                ) {
                    BoldText(textId = R.string.your_path)
                    NormalText(textId = R.string.your_path_description)
                    Spacer(modifier = Modifier.padding(8.dp))
                    PathModuleCard(
                        moduleTitleText = "Demo Module",
                        moduleDescriptionText = "This is a demo module to show how the module card looks like"
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    PathModuleCard(
                        moduleTitleText = "Demo Module2",
                        moduleDescriptionText = "This is a demo module to show how the module card looks like for the second module"
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GeneralButtonComponent(
                            valueId = R.string.looks_good,
                            onButtonClicked = {
                                //TODO: Send data to firebase - firestore
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    )
}