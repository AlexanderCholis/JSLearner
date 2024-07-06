package eu.tkacas.jslearner.presentation.model

data class NavigationDrawerUiItem (
    val id: Int,
    val name: String,
    val unselectedIcon: Int,
    val selectedIcon: Int,
    val route: String
)