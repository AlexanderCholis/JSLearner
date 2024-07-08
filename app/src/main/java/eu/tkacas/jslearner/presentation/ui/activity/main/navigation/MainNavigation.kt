package eu.tkacas.jslearner.presentation.ui.activity.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.AccountScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.LeaderboardScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.RoadMapScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.SettingsScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.StartDescriptionScreen
import eu.tkacas.jslearner.presentation.viewmodel.main.RoadMapViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartDescriptionViewModel

@Composable
internal fun MainNavigation(
    roadMapViewModel: RoadMapViewModel,
    startDescriptionViewModel: StartDescriptionViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "roadmap"
    ) {
        composable("roadmap") {
            RoadMapScreen(
                navController = navController,
                viewModel = roadMapViewModel
            )
        }
        composable("startDescription/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            StartDescriptionScreen(
                navController = navController,
                viewModel = startDescriptionViewModel,
                id = id
            )
        }
        composable("account") {
            AccountScreen(
                navController = navController
            )
        }
        composable("settings") {
            SettingsScreen(
                navController = navController
            )
        }
        composable("leaderboard") {
            LeaderboardScreen(
                navController = navController
            )
        }
    }

}