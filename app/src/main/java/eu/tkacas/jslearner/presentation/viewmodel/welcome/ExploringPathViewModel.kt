package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.GetCoursesBasedOnExperienceUseCase
import eu.tkacas.jslearner.domain.model.CourseShort
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.UpdateUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExploringPathViewModel(
    private val getCoursesBasedOnExperienceUseCase: GetCoursesBasedOnExperienceUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
): ViewModel() {

    private val _exploringPathState = MutableStateFlow<Result<List<CourseShort>>?>(null)
    val exploringPathState: StateFlow<Result<List<CourseShort>>?> = _exploringPathState

    fun returnCourses(experienceLevel: ExperienceLevel) = viewModelScope.launch {
        try {
            _exploringPathState.value = Result.Loading

            val courses = getCoursesBasedOnExperienceUseCase.execute(experienceLevel)
            _exploringPathState.value = Result.Success(courses)
        } catch (e: Exception) {
            _exploringPathState.value = Result.Error(e)
        }
    }

    fun updateUserProfile(learningReason: LearningReason, experienceLevel: ExperienceLevel) = viewModelScope.launch {
        try {
            updateUserProfileUseCase.execute(learningReason, true, experienceLevel)
        } catch (e: Exception) {
            _exploringPathState.value = Result.Error(e)
        }
    }
}