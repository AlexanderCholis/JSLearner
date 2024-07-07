package eu.tkacas.jslearner.presentation.ui.activity.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.LeaderboardComponent
import eu.tkacas.jslearner.presentation.ui.component.LeaderboardRowComponent
import eu.tkacas.jslearner.presentation.ui.component.LeaderboardTopSection
import eu.tkacas.jslearner.presentation.ui.component.MenuAppTopBar
import eu.tkacas.jslearner.presentation.ui.component.NavigationDrawer
import eu.tkacas.jslearner.presentation.ui.component.WinnersPodiumComponent
import kotlinx.coroutines.launch

@Composable
fun LeaderboardScreen(
    navController: NavController
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                            .fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        LeaderboardTopSection(image = R.drawable.application, userScore = 1000, leaderType1 = 1)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        LeaderboardRowComponent(
                            image2 = R.drawable.application,
                            score2 = 980,
                            leaderType2 = 2,
                            image3 = R.drawable.application,
                            score3 = 920,
                            leaderType3 = 3,
                        )
                    }
                    WinnersPodiumComponent()
                    Spacer(modifier = Modifier.height(30.dp))
                    // Add LeaderboardComponent entries here
                    LeaderboardComponent(userImage = R.drawable.application, userName = "User 1", userScore = 1000)
                    Spacer(modifier = Modifier.height(8.dp))
                    LeaderboardComponent(userImage = R.drawable.application, userName = "User 2", userScore = 980)
                    Spacer(modifier = Modifier.height(8.dp))
                    LeaderboardComponent(userImage = R.drawable.application, userName = "User 3", userScore = 920)
                }
            }
        }
    }
}