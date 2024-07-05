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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
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
import eu.tkacas.jslearner.presentation.ui.activity.main.MainActivity
import eu.tkacas.jslearner.presentation.ui.activity.welcome.WelcomeActivity
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(navController: NavController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    val selectedItem = remember { mutableStateOf(-1) }

    // Define your screens
    val screensInDrawer = listOf(
        NavigationDrawerUiItem(id = R.drawable.account, name = "Account", icon = R.drawable.account, route = "account"),
        NavigationDrawerUiItem(id = R.drawable.settings, name = "Settings", icon = R.drawable.settings, route = "settings"),
        NavigationDrawerUiItem(id = R.drawable.leaderboard, name = "Leaderboard", icon = R.drawable.leaderboard, route = "leaderboard")
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    CourseTopCard(
                        points = 500,
                        days = 12,
                        answers = 3
                    ) //should be given from the database

                    Spacer(Modifier.height(12.dp))

                    screensInDrawer.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            icon = {
                                Image(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = null
                                )
                            },
                            label = { Text(item.name) },
                            selected = selectedItem.value == item.id,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item.id
                                navController.navigate(item.route)
                            },
                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }

                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val context = LocalContext.current
                    Spacer(modifier = Modifier.height(12.dp))
                    NavigationDrawerItem(
                        icon = { Image(painter = painterResource(id = R.drawable.logout), contentDescription = null) },
                        label = { Text("Logout",  color = Color.Red) },
                        selected = selectedItem.value == R.drawable.logout,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = R.drawable.logout
                            //Sign out the user from Firebase
                            Firebase.auth.signOut()

                            //Navigate to WelcomeScreen through WelcomeActivity after logout

                            // Start WelcomeActivity
                            val intent = Intent(context, WelcomeActivity::class.java).apply{
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                            context.startActivity(intent)

                            // Finish the current activity (MainActivity) to prevent going back
                            (context as Activity).finish()
//                            navController.navigate("welcome") {
//                                popUpTo("welcome") { // Clear backstack to prevent going back to logged-in screens //0 instead of "welcome"
//                                    inclusive = true
//                                }
//                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            // Content
        }
    )
}
