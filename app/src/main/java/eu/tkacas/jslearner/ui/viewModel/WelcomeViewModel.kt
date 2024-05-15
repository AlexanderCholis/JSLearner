package eu.tkacas.jslearner.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.IWelcomeActions

class WelcomeViewModel(
    val welcomeActions: IWelcomeActions
): ViewModel() {
    companion object {
        fun provideFactory(
            welcomeActions: IWelcomeActions
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(WelcomeViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return WelcomeViewModel(welcomeActions) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}