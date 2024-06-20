package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.GetCoursesBasedOnExperienceUseCase

class ExploringPathViewModel(
    exploringPathRepository: ExploringPathRepository,
    private val getCoursesBasedOnExperienceUseCase: GetCoursesBasedOnExperienceUseCase
): ViewModel() {



    fun returnCourses(experienceLevel: ExperienceLevel) {

    }
}