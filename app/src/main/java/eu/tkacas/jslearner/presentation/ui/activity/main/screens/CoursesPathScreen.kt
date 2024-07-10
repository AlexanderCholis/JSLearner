package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.CoursesPathCard
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel

@Composable
fun CoursesPathScreen(
    navController: NavController,
    sharedViewModel: MainSharedViewModel
) {
    val courseStatuses = sharedViewModel.coursesState

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.courses),
                color = MaterialTheme.colorScheme.primaryContainer,
                onBackClick = { navController.navigateUp() },
                showScore = true,
                score = sharedViewModel.user.value?.experienceScore ?: 0
            )
        },
    ) { innerPadding ->
        Surface(
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                courseStatuses.value?.let {
                    items(it.size) { index ->
                        Spacer(modifier = Modifier.height(8.dp))
                        CoursesPathCard(
                            courseTitleText = courseStatuses.value?.get(index)?.title
                                ?: error("Course title not found"),
                            courseStatus = courseStatuses.value?.get(index)?.status
                                ?: error("Course status not found"),
                        )
                    }
                }
            }
        }
    }
}