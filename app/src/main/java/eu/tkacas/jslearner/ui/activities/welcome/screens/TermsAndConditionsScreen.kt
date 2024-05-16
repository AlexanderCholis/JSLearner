package eu.tkacas.jslearner.ui.activities.welcome.screens

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
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.ui.components.BackAppTopBar
import eu.tkacas.jslearner.ui.viewModel.TermsAndConditionsViewModel

@Composable
fun TermsAndConditionsScreen(
    viewModel: TermsAndConditionsViewModel
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