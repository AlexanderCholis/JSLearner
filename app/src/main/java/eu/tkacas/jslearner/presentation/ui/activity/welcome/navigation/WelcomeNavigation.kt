package eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.ExperienceLevelScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.ExperienceTextScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.ExploringPathScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.LearningReasonScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.PrivacyPolicyScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.TermsAndConditionsScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.WelcomeScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth.LoginScreen
import eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth.SignUpScreen
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExperienceLevelViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExperienceTextViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExploringPathViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.LearningReasonViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.LoginViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.SignUpViewModel

@Composable
internal fun WelcomeNavigation(
    authRepository: AuthRepository,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    experienceLevelViewModel: ExperienceLevelViewModel,
    exploringPathViewModel: ExploringPathViewModel,
    learningReasonViewModel: LearningReasonViewModel,
    experienceTextViewModel: ExperienceTextViewModel
){
    val navController = rememberNavController()
    val startDestination = determineStartDestination(authRepository)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("welcome") { WelcomeScreen(navController = navController) }
        composable("login") { LoginScreen(navController = navController,viewModel = loginViewModel) }
        composable("signUp") { SignUpScreen(navController = navController, viewModel = signUpViewModel) }
        composable("termsAndConditions") { TermsAndConditionsScreen(navController = navController) }
        composable("privacyPolicy") { PrivacyPolicyScreen(navController = navController) }
        composable("experienceLevel") { ExperienceLevelScreen(navController = navController, viewModel = experienceLevelViewModel) }
        composable("experienceText?experienceLevel={experienceLevel}") { backStackEntry ->
            val experienceLevelString = backStackEntry.arguments?.getString("experienceLevel")
            val experienceLevel = ExperienceLevel.valueOf(experienceLevelString!!)
            ExperienceTextScreen(navController = navController, viewModel = experienceTextViewModel, experienceLevel = experienceLevel)
        }
        composable("learningReason?experienceLevel={experienceLevel}") { backStackEntry ->
            val experienceLevel = backStackEntry.arguments?.getString("experienceLevel")
            LearningReasonScreen(navController = navController, viewModel = learningReasonViewModel, experienceLevel = experienceLevel!!)
        }
        composable("exploringPath?experienceLevel={experienceLevel}&selectedReason={selectedReason}") { backStackEntry ->
            val experienceLevelString = backStackEntry.arguments?.getString("experienceLevel")
            val experienceLevel = ExperienceLevel.valueOf(experienceLevelString!!)
            val selectedReasonString = backStackEntry.arguments?.getString("selectedReason")
            val selectedReason = LearningReason.valueOf(selectedReasonString!!)
            ExploringPathScreen(navController = navController, viewModel = exploringPathViewModel, experienceLevel = experienceLevel, selectedReason = selectedReason)
        }
    }
}

fun determineStartDestination(authRepository: AuthRepository): String {
    // TODO: Implement logic to determine the start destination
    return if (authRepository.currentUser != null) "experienceLevel" else "welcome"
}