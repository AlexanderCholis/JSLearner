package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.BoldText
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.LearningReasonCard
import eu.tkacas.jslearner.presentation.ui.component.NormalText
import eu.tkacas.jslearner.presentation.viewmodel.welcome.LearningReasonViewModel

@Composable
fun LearningReasonScreen(
    navController: NavController,
    viewModel: LearningReasonViewModel,
    experienceLevel: ExperienceLevel
) {
    val context = LocalContext.current
    val uiLearningReasons = viewModel.uiLearningReasons
    var selectedReason by rememberSaveable { mutableStateOf<LearningReason?>(null) }

    BackHandler {
        navController.popBackStack(navController.graph.startDestinationId, inclusive = false)
        navController.navigate("experienceLevel")
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                color = Color.White,
                onBackClick = {
                    navController.popBackStack(navController.graph.startDestinationId, inclusive = false)
                    navController.navigate("experienceLevel")
                }
            )
        },
        content = { padding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(top = 70.dp, bottom = 28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 32.dp, end = 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.faq),
                        contentDescription = stringResource(id = R.string.simple_image),
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(color = Color(0xFFF8EFE0))
                    )
                    NormalText(text = stringResource(id = R.string.finding_your_path))
                    Spacer(modifier = Modifier.height(8.dp))
                    BoldText(text = stringResource(id = R.string.why_do_you_want_to_learn))
                    Spacer(modifier = Modifier.height(8.dp))
                    uiLearningReasons.forEachIndexed { index, item ->
                        LearningReasonCard(
                            image = item.image,
                            text = item.text,
                            isSelected = selectedReason == item.reason,
                            onSelected = { selectedReason = item.reason }
                        )
                        if (index < uiLearningReasons.size - 1) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                    Column(
                        modifier= Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GeneralButtonComponent(
                            valueId = R.string.next,
                            onButtonClicked = {
                                if (selectedReason != null) {
                                    navController.navigate("exploringPath?experienceLevel=$experienceLevel&selectedReason=$selectedReason")
                                } else {
                                    Toast.makeText(context, "Please select a reason", Toast.LENGTH_SHORT).show()
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}