package eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.ExperienceLevelScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.PrivacyPolicyScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.TermsAndConditionsScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.WelcomeScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth.LoginScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth.SignUpScreen
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExperienceLevelViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.LoginViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.SignUpViewModel


@Composable
internal fun WelcomeNavigation(
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    experienceLevelViewModel: ExperienceLevelViewModel
){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") { WelcomeScreen(navController = navController) }
        composable("login") { LoginScreen(navController = navController,viewModel = loginViewModel) }
        composable("signUp") { SignUpScreen(navController = navController, viewModel = signUpViewModel) }
        composable("termsAndConditions") { TermsAndConditionsScreen(navController = navController) }
        composable("privacyPolicy") { PrivacyPolicyScreen(navController = navController) }
        composable("experienceLevel") { ExperienceLevelScreen(navController = navController, viewModel = experienceLevelViewModel) }
    }
}