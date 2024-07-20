package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.BackAppTopBar
import eu.tkacas.jslearner.presentation.ui.theme.SkyBlue

@Composable
fun TermsAndConditionsScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.terms_and_conditions_header),
                color = MaterialTheme.colorScheme.primaryContainer,
                onBackClick = {
                    navController.navigateUp()
                }
            )
        },
        content = { padding ->
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 28.dp, end = 28.dp, top = 18.dp)
                ) {
                    item {
                        Text(
                            text = stringResource(id = R.string.terms_of_use_intro)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_license)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_conduct)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_ip)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_liability)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_modifications)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_governing_law)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_contact_us)
                        )
                        Text(
                            text = stringResource(id = R.string.terms_of_use_acknowledgement)
                        )
                    }
                }
            }
        }
    )
}