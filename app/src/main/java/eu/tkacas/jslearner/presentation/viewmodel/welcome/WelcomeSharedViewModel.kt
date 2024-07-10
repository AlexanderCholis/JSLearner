package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason

class WelcomeSharedViewModel : ViewModel() {
    private val _experienceLevel = MutableLiveData<ExperienceLevel>()
    val experienceLevel: LiveData<ExperienceLevel> = _experienceLevel

    private val _selectedReason = MutableLiveData<LearningReason?>()
    val selectedReason: LiveData<LearningReason?> = _selectedReason

    fun setExperienceLevel(level: ExperienceLevel) {
        _experienceLevel.value = level
    }

    fun setSelectedReason(reason: LearningReason?) {
        _selectedReason.value = reason
    }
}