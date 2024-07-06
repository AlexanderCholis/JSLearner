package eu.tkacas.jslearner.domain.usecase.main

import eu.tkacas.jslearner.domain.repository.NavigationDrawerRepository
import eu.tkacas.jslearner.presentation.model.NavigationDrawerUiItem

class GetNavigationDrawerItemsUseCase(private val repository: NavigationDrawerRepository) {
    fun execute(): List<NavigationDrawerUiItem> {
        return repository.getNavigationDrawerItems()
    }
}