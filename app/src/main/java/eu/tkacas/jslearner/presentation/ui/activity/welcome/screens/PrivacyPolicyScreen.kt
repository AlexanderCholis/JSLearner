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
fun PrivacyPolicyScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BackAppTopBar(
                title = stringResource(id = R.string.privacy_policy_header),
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
                    .padding(start = 28.dp, end = 28.dp, top = 88.dp, bottom = 28.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item{
                        Text(
                            text = stringResource(id = R.string.privacy_policy_intro)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_info_collect)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_use_info)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_share_info)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_data_security)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_children)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_changes)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_contact_us)
                        )
                        Text(
                            text = stringResource(id = R.string.privacy_policy_acknowledgement)
                        )
                    }
                }
            }
        }
    )
}