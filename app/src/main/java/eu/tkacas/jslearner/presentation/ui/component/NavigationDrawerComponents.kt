package eu.tkacas.jslearner.presentation.ui.component

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
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.usecase.main.GetNavigationDrawerItemsUseCase
import eu.tkacas.jslearner.domain.usecase.user.LogoutUseCase
import eu.tkacas.jslearner.presentation.model.NavigationDrawerUiItem
import eu.tkacas.jslearner.presentation.ui.activity.welcome.WelcomeActivity
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navController: NavController,
    drawerState: DrawerState,
    getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase,
    logoutUseCase: LogoutUseCase
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Define your screens
    val screensInDrawer = getNavigationDrawerItemsUseCase.execute()

    fun getCurrentRouteIndex(): Int {
        val currentRoute = navController.currentDestination?.route
        return screensInDrawer.indexOfFirst { it.route == currentRoute }
    }

    // Use the function to get the current route index
    val selectedItemIndex = remember { mutableIntStateOf(getCurrentRouteIndex()) }

    ModalDrawerSheet {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            screensInDrawer.forEachIndexed { index, item ->
                DrawerItem(
                    item = item,
                    isSelected = selectedItemIndex.intValue == index,
                    isEnabled = selectedItemIndex.intValue != index,
                    onItemClick = {
                        scope.launch { drawerState.close() }
                        selectedItemIndex.intValue = index
                        item.route?.let { navController.navigate(it) }
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
            DrawerItem(
                item = NavigationDrawerUiItem(
                    name = stringResource(id = R.string.logout),
                    unselectedIcon = R.drawable.logout
                ),
                isSelected = selectedItemIndex.intValue == screensInDrawer.size,
                textColor = Color.Red,
                onItemClick = {
                    scope.launch {
                        drawerState.close()

                        selectedItemIndex.intValue = screensInDrawer.size

                        logoutUseCase.execute()

                        val intent = Intent(context, WelcomeActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        context.startActivity(intent)
                    }
                }
            )
        }
    }
}

@Composable
fun DrawerItem(
    item: NavigationDrawerUiItem,
    isSelected: Boolean,
    isEnabled: Boolean = true,
    textColor: Color = if (item.name == "Logout") Color.Red else Color.Black,
    onItemClick: () -> Unit
) {
    val iconId = if (isSelected && item.selectedIcon != null) {
        item.selectedIcon
    } else {
        item.unselectedIcon
    }

    NavigationDrawerItem(
        icon = {
            Image(
                painter = painterResource(id = iconId),
                contentDescription = item.name
            )
        },
        label = {
            Text(
                text = item.name,
                fontSize = 18.sp,
                color = textColor.copy(alpha = if (isSelected) 1f else 0.6f)
            )
        },
        selected = isSelected,
        onClick = {
            if (isEnabled) {
                onItemClick()
            }
        }
    )
}
