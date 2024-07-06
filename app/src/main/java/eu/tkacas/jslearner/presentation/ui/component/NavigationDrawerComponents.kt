package eu.tkacas.jslearner.presentation.ui.component

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.Text
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.model.NavigationDrawerUiItem
import eu.tkacas.jslearner.presentation.ui.activity.welcome.WelcomeActivity
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navController: NavController,
    drawerState: DrawerState
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val selectedItem = remember { mutableStateOf(-1) }

    // Define your screens
    val screensInDrawer = listOf(
        NavigationDrawerUiItem(id = R.drawable.roadmap, name = "Roadmap", unselectedIcon = R.drawable.roadmap, selectedIcon = R.drawable.roadmap_filled, route = "roadmap"),
        NavigationDrawerUiItem(id = R.drawable.account, name = "Account", unselectedIcon = R.drawable.account, selectedIcon = R.drawable.account_filled, route = "account"),
        NavigationDrawerUiItem(id = R.drawable.settings, name = "Settings", unselectedIcon = R.drawable.settings,selectedIcon = R.drawable.settings_filled, route = "settings"),
        NavigationDrawerUiItem(id = R.drawable.leaderboard, name = "Leaderboard", unselectedIcon = R.drawable.leaderboard,selectedIcon = R.drawable.leaderboard_filled, route = "leaderboard")
    )

    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            CourseTopCard(
                points = 500,
                days = 12,
                answers = 3
            ) //should be given from the database

            Spacer(Modifier.height(12.dp))

            screensInDrawer.forEachIndexed { index, item ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.unselectedIcon),
                            contentDescription = null
                        )
                    },
                    label = { Text(item.name) },
                    selected = selectedItem.value == item.id,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item.id
                        navController.navigate(item.route)
                    }
                )
            }
        }

        Column(
            Modifier.fillMaxSize() .padding(12.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            NavigationDrawerItem(
                icon = {
                    Image(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = null) },
                label = {
                    Text(
                        text = "Logout",
                        color = Color.Red
                    )
                },
                selected = selectedItem.value == R.drawable.logout,
                onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    selectedItem.value = R.drawable.logout

                    Firebase.auth.signOut()

                    val intent = Intent(context, WelcomeActivity::class.java).apply{
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}
