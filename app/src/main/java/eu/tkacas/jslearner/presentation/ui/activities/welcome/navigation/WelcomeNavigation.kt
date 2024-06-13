package eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.domain.usecase.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.ValidateTerms
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.actions.ILoginActions
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.actions.IPrivacyPolicyActions
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.actions.ISignUpActions
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.actions.ITermsAndConditionsActions
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.actions.IWelcomeActions
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.objects.Login
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.objects.PrivacyPolicy
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.objects.SignUp
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.objects.TermsAndConditions
import eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation.objects.Welcome
import eu.tkacas.jslearner.presentation.ui.activities.welcome.screens.PrivacyPolicyScreen
import eu.tkacas.jslearner.presentation.ui.activities.welcome.screens.TermsAndConditionsScreen
import eu.tkacas.jslearner.presentation.ui.activities.welcome.screens.WelcomeScreen
import eu.tkacas.jslearner.presentation.ui.activities.welcome.screens.auth.LoginScreen
import eu.tkacas.jslearner.presentation.ui.activities.welcome.screens.auth.SignUpScreen
import eu.tkacas.jslearner.presentation.ui.viewModel.PrivacyPolicyViewModel
import eu.tkacas.jslearner.presentation.ui.viewModel.TermsAndConditionsViewModel
import eu.tkacas.jslearner.presentation.ui.viewModel.WelcomeViewModel
import eu.tkacas.jslearner.presentation.ui.viewModel.auth.LoginViewModel
import eu.tkacas.jslearner.presentation.ui.viewModel.auth.SignUpViewModel

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