package eu.tkacas.jslearner.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.tkacas.jslearner.ui.activities.welcome.navigation.actions.ITermsAndConditionsActions

class TermsAndConditionsViewModel(
    val termsAndConditionsActions: ITermsAndConditionsActions
): ViewModel() {
    companion object {
        fun provideFactory(
            termsAndConditionsActions: ITermsAndConditionsActions
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(TermsAndConditionsViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return TermsAndConditionsViewModel(termsAndConditionsActions) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}