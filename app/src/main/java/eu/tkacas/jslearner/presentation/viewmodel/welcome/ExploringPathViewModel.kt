package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.GetCoursesBasedOnExperienceUseCase
import eu.tkacas.jslearner.presentation.model.PathCardUiItem

class ExploringPathViewModel(
    exploringPathRepository: ExploringPathRepository,
    private val getCoursesBasedOnExperienceUseCase: GetCoursesBasedOnExperienceUseCase
): ViewModel() {

    private val _coursesUiList = mutableListOf<PathCardUiItem>()
    val coursesUiList: List<PathCardUiItem> = _coursesUiList

    suspend fun returnCourses(experienceLevel: ExperienceLevel) {
        val coursesList = getCoursesBasedOnExperienceUseCase.execute(experienceLevel)
        coursesList.forEach {
            _coursesUiList.add(
                PathCardUiItem(
                    title = it.title,
                    description = it.descriptionShort
                )
            )
        }
    }
}