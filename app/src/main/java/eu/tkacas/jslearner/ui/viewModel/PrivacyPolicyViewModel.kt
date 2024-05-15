package eu.tkacas.jslearner.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.IPrivacyPolicyActions

class PrivacyPolicyViewModel(
    val privacyPolicyActions: IPrivacyPolicyActions
): ViewModel() {
    companion object {
        fun provideFactory(
            privacyPolicyActions: IPrivacyPolicyActions
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(PrivacyPolicyViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return PrivacyPolicyViewModel(privacyPolicyActions) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}