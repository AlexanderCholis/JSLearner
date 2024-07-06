package eu.tkacas.jslearner.data.repository

import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.repository.NavigationDrawerRepository
import eu.tkacas.jslearner.presentation.model.NavigationDrawerUiItem

class NavigationDrawerRepositoryImpl: NavigationDrawerRepository {
    override fun getNavigationDrawerItems()= listOf(
        NavigationDrawerUiItem(name = "Roadmap", unselectedIcon = R.drawable.roadmap, selectedIcon = R.drawable.roadmap_filled, route = "roadmap"),
        NavigationDrawerUiItem(name = "Account", unselectedIcon = R.drawable.account, selectedIcon = R.drawable.account_filled, route = "account"),
        NavigationDrawerUiItem(name = "Settings", unselectedIcon = R.drawable.settings,selectedIcon = R.drawable.settings_filled, route = "settings"),
        NavigationDrawerUiItem(name = "Leaderboard", unselectedIcon = R.drawable.leaderboard,selectedIcon = R.drawable.leaderboard_filled, route = "leaderboard")
    )
}