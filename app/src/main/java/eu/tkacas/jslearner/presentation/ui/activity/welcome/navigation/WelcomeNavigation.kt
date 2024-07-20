package eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.user.GetProfileCompletionUseCase
import eu.tkacas.jslearner.presentation.ui.activity.main.MainActivity
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
import eu.tkacas.jslearner.presentation.viewmodel.welcome.WelcomeSharedViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.LoginViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.SignUpViewModel
import kotlinx.coroutines.runBlocking

@Composable
internal fun WelcomeNavigation(
    context: Context,
    authRepository: AuthRepository,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    experienceLevelViewModel: ExperienceLevelViewModel,
    exploringPathViewModel: ExploringPathViewModel,
    learningReasonViewModel: LearningReasonViewModel,
    experienceTextViewModel: ExperienceTextViewModel,
    getProfileCompletionUseCase: GetProfileCompletionUseCase
) {
    val navController = rememberNavController()
    val sharedViewModel = WelcomeSharedViewModel()
    val startDestination = runBlocking {
        determineStartDestination(
            context,
            authRepository,
            getProfileCompletionUseCase
        )
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("welcome") {
            WelcomeScreen(
                navController = navController
            )
        }
        composable("login") {
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }
        composable("signUp") {
            SignUpScreen(
                navController = navController,
                viewModel = signUpViewModel
            )
        }
        composable("termsAndConditions") {
            TermsAndConditionsScreen(
                navController = navController
            )
        }
        composable("privacyPolicy") {
            PrivacyPolicyScreen(
                navController = navController
            )
        }
        composable("experienceLevel") {
            ExperienceLevelScreen(
                navController = navController,
                viewModel = experienceLevelViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("experienceText") {
            ExperienceTextScreen(
                navController = navController,
                viewModel = experienceTextViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("learningReason") {
            LearningReasonScreen(
                navController = navController,
                viewModel = learningReasonViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("exploringPath") {
            ExploringPathScreen(
                navController = navController,
                viewModel = exploringPathViewModel,
                sharedViewModel = sharedViewModel
            )
        }
    }
}

suspend fun determineStartDestination(
    context: Context,
    authRepository: AuthRepository,
    getProfileCompletionUseCase: GetProfileCompletionUseCase
): String {
    // If the user is not logged in, navigate to the welcome screen
    authRepository.currentUser ?: return "welcome"

    // If the user's profile is completed, start the main activity
    // Otherwise, navigate to the experience level screen
    return if (determineActivity(context, getProfileCompletionUseCase)) {
        "welcome" // Return "welcome" to prevent the NavHost from navigating to another destination
    } else {
        "experienceLevel"
    }
}

suspend fun determineActivity(
    context: Context,
    getProfileCompletionUseCase: GetProfileCompletionUseCase
): Boolean {
    return try {
        val isProfileComplete = getProfileCompletionUseCase.execute()
        if (isProfileComplete) {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)
            true
        } else {
            false
        }
    } catch (e: Exception) {
        false
    }
}