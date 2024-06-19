package eu.tkacas.jslearner.presentation.ui.component

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import eu.tkacas.jslearner.presentation.ui.activity.welcome.WelcomeActivity
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(navController: NavController, drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    val items =
        listOf(
            R.drawable.account,
            R.drawable.settings,
            R.drawable.stats,
            R.drawable.logout, //(change it to red color)
        )
    //val selectedItem = remember { mutableStateOf(items[0]) }
    val selectedItem = remember { mutableStateOf(-1) }
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet() {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    Spacer(Modifier.height(12.dp))

                    CourseTopCard(
                        points = 500,
                        days = 12,
                        answers = 3
                    ) //should be given from the database

                    Spacer(Modifier.height(12.dp))

                    NavigationDrawerItem(
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.account),
                                contentDescription = null
                            )
                        },
                        label = { Text("Account") },
                        selected = selectedItem.value == R.drawable.account,
                        onClick = {
                            //navController.navigate("account")
                            scope.launch { drawerState.close() }
                            selectedItem.value = R.drawable.account
                            if (selectedItem.value == R.drawable.account) {
                                navController.navigate("account")
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.settings),
                                contentDescription = null
                            )
                        },
                        label = { Text("Settings") },
                        selected = selectedItem.value == R.drawable.settings,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = R.drawable.settings
                            navController.navigate("settings")
                            if (selectedItem.value == R.drawable.settings) {
                                navController.navigate("settings")
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )

                    NavigationDrawerItem(
                        icon = {
                            Image(
                                painter = painterResource(id = R.drawable.stats),
                                contentDescription = null
                            )
                        },
                        label = { Text("Stats") },
                        selected = selectedItem.value == R.drawable.stats,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = R.drawable.stats
                            navController.navigate("stats")
                            if (selectedItem.value == R.drawable.stats) {
                                navController.navigate("stats")
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
                Column(Modifier.fillMaxSize()) {
                    //val context = LocalContext.current
                    Spacer(Modifier.weight(1f))
                    NavigationDrawerItem(
                        icon = { Image(painter = painterResource(id = R.drawable.logout), contentDescription = null) },
                        label = { Text("Logout",  color = Color.Red) },
                        selected = selectedItem.value == R.drawable.logout,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = R.drawable.logout
                            //Firebase.auth.signOut()

                            //Navigate to WelcomeScreen through WelcomeActivity after logout

                            // Start WelcomeActivity
//                            val intent = Intent(context, WelcomeActivity::class.java)
//                            context.startActivity(intent)

                            // Finish the current activity (MainActivity) to prevent going back
//                            (context as Activity).finish()

//                            navController.navigate("welcome") { // Navigate to login after logout
//                                popUpTo(0) {  // Clear backstack to prevent going back to logged-in screens
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Text(text = if (drawerState.isClosed) ">>> Swipe >>>" else "<<< Swipe <<<")
                //Spacer(Modifier.height(20.dp))
                //Button(onClick = { scope.launch { drawerState.open() } }) { Text("Click to open") }
            }
        }
    )
}