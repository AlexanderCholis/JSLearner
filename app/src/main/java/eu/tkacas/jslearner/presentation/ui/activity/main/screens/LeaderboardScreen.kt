package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.PodiumUser
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.presentation.ui.component.LeaderboardCard
import eu.tkacas.jslearner.presentation.ui.component.MenuAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.NavigationDrawer
import eu.tkacas.jslearner.presentation.ui.component.WinnersPodiumComponentWithLeaders
import eu.tkacas.jslearner.presentation.viewmodel.main.AccountViewModel
import kotlinx.coroutines.launch

@Composable
fun LeaderboardScreen(
    navController: NavController,
    viewModel: AccountViewModel // TODO: Change viewModel to get the list of users and change also from MainNavigation.kt
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()
    val user = (uiState as Result.Success<User?>).result

    // TODO: Use viewModel to get the list of users
    val podiumUserList = listOf(
        PodiumUser(user?.firstName ?: "Unknown", user?.lastName ?: "User", 1120, 1),
        PodiumUser(user?.firstName ?: "Unknown", user?.lastName ?: "User", 1100, 2),
        PodiumUser(user?.firstName ?: "Unknown", user?.lastName ?: "User", 1080, 3),
    )

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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    // First 3 places on the podium
                    WinnersPodiumComponentWithLeaders(podiumUserList)
                    Spacer(modifier = Modifier.height(30.dp))
                    // Other users on the leaderboard
                    LeaderboardCard(
                        firstName = user?.firstName ?: "Unknown",
                        lastName = user?.lastName ?: "User",
                        userName = "${user?.firstName ?: "Unknown"} ${user?.lastName ?: "User"}",
                        userScore = 1020,
                        position = 4
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LeaderboardCard(
                        firstName = user?.firstName ?: "Unknown",
                        lastName = user?.lastName ?: "User",
                        userName = "${user?.firstName ?: "Unknown"} ${user?.lastName ?: "User"}",
                        userScore = 980,
                        position = 5
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LeaderboardCard(
                        firstName = user?.firstName ?: "Unknown",
                        lastName = user?.lastName ?: "User",
                        userName = "${user?.firstName ?: "Unknown"} ${user?.lastName ?: "User"}",
                        userScore = 920,
                        position = 6
                    )
                }
            }
        }
    }
}