package eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.objects.Login
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.objects.PrivacyPolicy
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.objects.SignUp
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.objects.TermsAndConditions
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.objects.Welcome
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.PrivacyPolicyScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.TermsAndConditionsScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.WelcomeScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth.LoginScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth.SignUpScreen

@Composable
internal fun WelcomeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Welcome
    ) {
        composable<Welcome> { WelcomeScreen(navController = navController) }
        composable<Login> { LoginScreen(navController = navController) }
        composable<SignUp> { SignUpScreen(navController = navController) }
        composable<TermsAndConditions> { TermsAndConditionsScreen(navController = navController) }
        composable<PrivacyPolicy> { PrivacyPolicyScreen(navController = navController) }
    }
}