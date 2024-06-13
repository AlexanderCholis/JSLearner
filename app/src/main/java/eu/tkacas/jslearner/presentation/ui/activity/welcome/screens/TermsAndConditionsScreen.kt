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
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.actions.ITermsAndConditionsActions
import eu.tkacas.jslearner.presentation.ui.activity.welcome.navigation.objects.SignUp
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.viewmodel.welcome.TermsAndConditionsViewModel

@Composable
fun TermsAndConditionsScreen(
    navController: NavController,
    viewModel: TermsAndConditionsViewModel = viewModel(factory = TermsAndConditionsViewModel.provideFactory(
        termsAndConditionsActions = object : ITermsAndConditionsActions {
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
                title = stringResource(id = R.string.terms_and_conditions_header),
                onBackClick = {
                    viewModel.termsAndConditionsActions.navigateGoBack()
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