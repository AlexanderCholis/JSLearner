package eu.tkacas.jslearner.presentation.ui.activity.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.AccountScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.RoadMapScreen
import eu.tkacas.jslearner.presentation.viewmodel.main.RoadMapViewModel

@Composable
internal fun MainNavigation(
    roadMapViewModel: RoadMapViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "roadmap"
    ) {
        composable("roadmap") { RoadMapScreen(navController = navController, viewModel = roadMapViewModel) }
        composable("account") { AccountScreen(navController = navController) }
    }

}