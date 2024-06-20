package eu.tkacas.jslearner.domain.usecase.welcome.exploringpath

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository
import eu.tkacas.jslearner.domain.model.CourseShort

class GetCoursesBasedOnExperienceUseCase(private val exploringPathRepository: ExploringPathRepository) {
    suspend fun execute(experienceLevel: ExperienceLevel): MutableList<CourseShort> {
        val courses = exploringPathRepository.getCoursesBasedOnExperience(experienceLevel)

        val coursesList = mutableListOf<CourseShort>()

        courses.forEach {
            coursesList.add(
                CourseShort(
                    title = it.title,
                    description = it.descriptionShort
                )
            )
        }

        return coursesList
    }
}