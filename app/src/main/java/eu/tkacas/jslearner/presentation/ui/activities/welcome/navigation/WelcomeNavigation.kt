package eu.tkacas.jslearner.presentation.ui.activities.welcome.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
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
                        navController.navigate(TermsAndConditions)
                    }

                    override fun navigateToPrivacy() {
                        navController.navigate(PrivacyPolicy)
                    }
                }
            ))
            val state = viewModel.state
            SignUpScreen(
                viewModel = viewModel,
                state = state
            )
        }
        composable<TermsAndConditions> {
            val viewModel: TermsAndConditionsViewModel = viewModel(factory = TermsAndConditionsViewModel.provideFactory(
                termsAndConditionsActions = object : ITermsAndConditionsActions {
                    override fun navigateToSignUp() {
                        navController.navigate(SignUp)
                    }

                    override fun navigateGoBack() {
                        navController.navigateUp()
                    }
                }
            ))
            TermsAndConditionsScreen(
                viewModel = viewModel
            )
        }
        composable<PrivacyPolicy> {
            val viewModel: PrivacyPolicyViewModel = viewModel(factory = PrivacyPolicyViewModel.provideFactory(
                privacyPolicyActions = object : IPrivacyPolicyActions {
                    override fun navigateToSignUp() {
                        navController.navigate(SignUp)
                    }

                    override fun navigateGoBack() {
                        navController.navigateUp()
                    }
                }
            ))
            PrivacyPolicyScreen(
                viewModel = viewModel
            )
        }
    }
}