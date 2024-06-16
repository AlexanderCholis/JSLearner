package eu.tkacas.jslearner.presentation.ui.activity.welcome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.WelcomeNavigation
import eu.tkacas.jslearner.presentation.ui.theme.JSLearnerTheme
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.LoginViewModel
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.SignUpViewModel
import eu.tkacas.jslearner.presentation.viewmodel.viewModelFactory
import eu.tkacas.jslearner.presentation.viewmodel.welcome.ExperienceLevelViewModel

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JSLearnerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val loginViewModel = viewModel<LoginViewModel>(
                        factory = viewModelFactory {
                            LoginViewModel(
                                authRepository = JSLearner.appModule.authRepository,
                                validateEmail = JSLearner.appModule.validateEmail,
                                validatePassword = JSLearner.appModule.validatePassword
                            )
                        }
                    )
                    val signUpViewModel = viewModel<SignUpViewModel>(
                        factory = viewModelFactory {
                            SignUpViewModel(
                                authRepository = JSLearner.appModule.authRepository,
                                validateEmail = JSLearner.appModule.validateEmail,
                                validatePassword = JSLearner.appModule.validatePassword,
                                validateFirstName = JSLearner.appModule.validateFirstName,
                                validateLastName = JSLearner.appModule.validateLastName,
                                validateTerms = JSLearner.appModule.validateTerms
                            )
                        }
                    )
                    val experienceLevelViewModel = viewModel<ExperienceLevelViewModel>()

                    WelcomeNavigation(
                        authRepository = JSLearner.appModule.authRepository,
                        loginViewModel = loginViewModel,
                        signUpViewModel = signUpViewModel,
                        experienceLevelViewModel = experienceLevelViewModel
                    )
                }
            }
        }
    }
}