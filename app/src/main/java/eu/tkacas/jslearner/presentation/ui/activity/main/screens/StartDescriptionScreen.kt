package eu.tkacas.jslearner.presentation.ui.activity.main.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.BulletText
import eu.tkacas.jslearner.presentation.ui.theme.componentShapes
import eu.tkacas.jslearner.presentation.viewmodel.main.RoadMapViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartDescriptionViewModel

@Composable
fun StartDescriptionScreen(
    navController: NavController,
    viewModel: StartDescriptionViewModel
) {
    val subsections = listOf("L1: Title", "L2: Title", "L3: Title", "L4: Title")

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(componentShapes.medium)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp, start = 36.dp, end = 36.dp, bottom = 16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Course Curriculum",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(bottom = 40.dp)
                        )
                    }
                    subsections.forEach {
                        BulletText(value = it)
                    }
                    Spacer(modifier = Modifier.height(100.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.application),
                            contentDescription = stringResource(id = R.string.simple_image),
                            modifier = Modifier.size(240.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Course Description Long",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GeneralButtonComponent(
                            valueId = R.string.start_course,
                            onButtonClicked = { }
                        )
                    }
                }
            }
        }
    }
}