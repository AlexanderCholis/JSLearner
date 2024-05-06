package eu.tkacas.jslearner.ui.activities.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.ui.activities.main.screens.RoadMapScreen


@Composable
internal fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { RoadMapScreen() }
    }
}