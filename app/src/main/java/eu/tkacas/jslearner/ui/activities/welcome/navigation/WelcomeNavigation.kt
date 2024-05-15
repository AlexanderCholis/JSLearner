package eu.tkacas.jslearner.ui.activities.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.domain.use_case.ValidateEmail
import eu.tkacas.jslearner.domain.use_case.ValidatePassword
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.ILoginActions
import eu.tkacas.jslearner.ui.activities.welcome.screens.auth.LoginScreen
import eu.tkacas.jslearner.ui.activities.welcome.screens.auth.SignUpScreen
import eu.tkacas.jslearner.ui.viewModel.auth.LoginViewModel
import kotlinx.serialization.Serializable

@Composable
internal fun WelcomeNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login
    ) {
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
        composable<SignUp> { SignUpScreen() }
    }
}

@Serializable
object Login

@Serializable
object SignUp