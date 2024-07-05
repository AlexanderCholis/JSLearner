package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.ExperienceLevelCard
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExperienceLevelViewModel

@Composable
fun ExperienceLevelScreen(
    navController: NavController,
    viewModel: ExperienceLevelViewModel
) {
    val uiLevels = viewModel.uiLevels

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier= Modifier
                .fillMaxSize()
                .padding(top = 60.dp, start = 32.dp, end = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.experience_level_until_now),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )
            uiLevels.forEachIndexed { _, item ->
                Spacer(modifier = Modifier.height(16.dp))
                ExperienceLevelCard(
                    image = item.image,
                    text = item.text,
                    onClick = {
                        navController.navigate("experienceText?experienceLevel=${item.level}")
                    }
                )
            }
        }
    }
}