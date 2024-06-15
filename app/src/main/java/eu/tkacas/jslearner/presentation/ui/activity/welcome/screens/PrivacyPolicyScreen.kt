package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.actions.IPrivacyPolicyActions
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.objects.SignUp
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.viewmodel.welcome.PrivacyPolicyViewModel

@Composable
fun PrivacyPolicyScreen(
    navController: NavController,
    viewModel: PrivacyPolicyViewModel = viewModel(factory = PrivacyPolicyViewModel.provideFactory(
        privacyPolicyActions = object : IPrivacyPolicyActions {
            override fun navigateToSignUp() {
                navController.navigate(SignUp)
            }

            override fun navigateGoBack() {
                navController.navigateUp()
            }
        }
    ))
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.privacy_policy_header),
                onBackClick = {
                    viewModel.privacyPolicyActions.navigateGoBack()
                }
            )
        },
        content = { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Terms and Conditions"
                    )
                }
            }
        }
    )
}