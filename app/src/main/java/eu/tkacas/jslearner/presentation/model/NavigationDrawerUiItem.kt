package eu.tkacas.jslearner.presentation.model

data class NavigationDrawerUiItem (
    val name: String,
    val unselectedIcon: Int,
    val selectedIcon: Int? = null,
    val route: String? = null
)