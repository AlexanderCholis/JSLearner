package eu.tkacas.jslearner.domain.repository

import eu.tkacas.jslearner.presentation.model.NavigationDrawerUiItem

interface NavigationDrawerRepository {
    fun getNavigationDrawerItems(): List<NavigationDrawerUiItem>
}