package eu.tkacas.jslearner.ui.viewModel.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseAuthViewModel: ViewModel() {
    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }

    protected val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
}