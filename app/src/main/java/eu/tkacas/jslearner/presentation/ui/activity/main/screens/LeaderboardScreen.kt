package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.data.model.LeaderboardUser
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.LeaderboardCardData
import eu.tkacas.jslearner.domain.model.PodiumUser
import eu.tkacas.jslearner.presentation.ui.component.LeaderboardCard
import eu.tkacas.jslearner.presentation.ui.component.MenuAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.NavigationDrawer
import eu.tkacas.jslearner.presentation.ui.component.WinnersPodiumComponentWithLeaders
import eu.tkacas.jslearner.presentation.viewmodel.main.LeaderboardViewModel
import kotlinx.coroutines.launch

@Composable
fun LeaderboardScreen(
    navController: NavController,
    viewModel: LeaderboardViewModel
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.loadLeaderboardData()
    }

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                drawerState = drawerState,
                getNavigationDrawerItemsUseCase = JSLearner.appModule.getNavigationDrawerItemsUseCase,
                logoutUseCase = JSLearner.appModule.logoutUseCase
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = {
                MenuAppTopBar(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    onMenuClick = {
                        scope.launch {
                            if (drawerState.isOpen) {
                                drawerState.close()
                            } else {
                                drawerState.open()
                            }
                        }
                    },
                    title = stringResource(id = R.string.leaderboard),
                    drawerState = drawerState,
                    showScore = false
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (uiState) {
                        is Result.Loading -> {
                            CircularProgressIndicator()
                        }

                        is Result.Error -> {
                            // Show an error message if something goes wrong
                            Text(
                                text = stringResource(id = R.string.an_error_occurred),
                                color = Color.Red,
                                textAlign = TextAlign.Center
                            )
                        }

                        is Result.Success -> {
                            val leaderboardUsers =
                                (uiState as Result.Success<List<LeaderboardUser>>).result.sortedByDescending { it.score }

                            // Extract the top 3 podium users
                            val podiumUsers = leaderboardUsers.take(3).mapIndexed { index, user ->
                                PodiumUser(
                                    firstName = user.firstName,
                                    lastName = user.lastName,
                                    score = user.score,
                                    position = index + 1
                                )
                            }

                            val users = leaderboardUsers.mapIndexed { index, user ->
                                LeaderboardCardData(
                                    firstName = user.firstName,
                                    lastName = user.lastName,
                                    userScore = user.score,
                                    position = index + 1
                                )
                            }


                            LazyColumn(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                item {
                                    WinnersPodiumComponentWithLeaders(podiumUsers)
                                    Spacer(modifier = Modifier.height(30.dp))
                                }

                                // Other users on the leaderboard
                                items(users) { user ->
                                    LeaderboardCard(
                                        firstName = user.firstName,
                                        lastName = user.lastName,
                                        userScore = user.userScore,
                                        position = user.position
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
