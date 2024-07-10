package eu.tkacas.jslearner.domain.model

import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.repository.NavigationDrawerRepository
import eu.tkacas.jslearner.presentation.model.NavigationDrawerUiItem

class NavigationDrawer : NavigationDrawerRepository {
    override fun getNavigationDrawerItems() = listOf(
        NavigationDrawerUiItem(
            name = "Roadmap",
            unselectedIcon = R.drawable.roadmap,
            selectedIcon = R.drawable.roadmap_filled,
            route = "roadmap"
        ),
        NavigationDrawerUiItem(
            name = "Account",
            unselectedIcon = R.drawable.account,
            selectedIcon = R.drawable.account_filled,
            route = "account"
        ),
        NavigationDrawerUiItem(
            name = "Leaderboard",
            unselectedIcon = R.drawable.leaderboard,
            selectedIcon = R.drawable.leaderboard_filled,
            route = "leaderboard"
        ),
        NavigationDrawerUiItem(
            name = "About",
            unselectedIcon = R.drawable.info,
            selectedIcon = R.drawable.info_filled,
            route = "about"
        ),
    )
}