package eu.tkacas.jslearner.ui.activities.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.domain.use_case.ValidateEmail
import eu.tkacas.jslearner.domain.use_case.ValidateFirstName
import eu.tkacas.jslearner.domain.use_case.ValidateLastName
import eu.tkacas.jslearner.domain.use_case.ValidatePassword
import eu.tkacas.jslearner.domain.use_case.ValidateTerms
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.ILoginActions
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.ISignUpActions
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.IWelcomeActions
import eu.tkacas.jslearner.ui.activities.welcome.navigation.objects.Login
import eu.tkacas.jslearner.ui.activities.welcome.navigation.objects.SignUp
import eu.tkacas.jslearner.ui.activities.welcome.navigation.objects.Welcome
import eu.tkacas.jslearner.ui.activities.welcome.screens.WelcomeScreen
import eu.tkacas.jslearner.ui.activities.welcome.screens.auth.LoginScreen
import eu.tkacas.jslearner.ui.activities.welcome.screens.auth.SignUpScreen
import eu.tkacas.jslearner.ui.viewModel.WelcomeViewModel
import eu.tkacas.jslearner.ui.viewModel.auth.LoginViewModel
import eu.tkacas.jslearner.ui.viewModel.auth.SignUpViewModel
import kotlinx.serialization.Serializable

@Composable
internal fun WelcomeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Welcome
    ) {
        composable<Welcome> {
            val viewModel: WelcomeViewModel = viewModel(factory = WelcomeViewModel.provideFactory(
                    welcomeActions = object : IWelcomeActions {
                        override fun navigateToLogin() {
                            navController.navigate(Login)
                        }
                    }
                )
            )
            WelcomeScreen(
                viewModel = viewModel
            )
        }
        composable<Login> {
            val viewModel: LoginViewModel = viewModel(factory =
            LoginViewModel.provideFactory(
                validateEmail = ValidateEmail(),
                validatePassword = ValidatePassword(),
                loginActions = object : ILoginActions {
                    override fun navigateToSignUp() {
                        navController.navigate(SignUp)
                    }
                }
            ))
            val state = viewModel.state
            LoginScreen(
                viewModel = viewModel,
                state = state
            )
        }
        composable<SignUp> {
            val viewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.provideFactory(
                validateFirstName = ValidateFirstName(),
                validateLastName = ValidateLastName(),
                validateEmail = ValidateEmail(),
                validatePassword = ValidatePassword(),
                validateTerms = ValidateTerms(),
                signUpActions = object : ISignUpActions {
                    override fun navigateToLogin() {
                        navController.navigate(Login)
                    }

                    override fun navigateToTerms() {
                        TODO("Not yet implemented")
                    }

                    override fun navigateToPrivacy() {
                        TODO("Not yet implemented")
                    }
                }
            ))
            val state = viewModel.state
            SignUpScreen(
                viewModel = viewModel,
                state = state
            )
        }
    }
}