package eu.tkacas.jslearner.domain.usecase.welcome.exploringpath

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository

class GetCoursesBasedOnExperienceUseCase(private val exploringPathRepository: ExploringPathRepository) {
    suspend fun execute(experienceLevel: ExperienceLevel): List<Course> {
        return exploringPathRepository.getCoursesBasedOnExperience(experienceLevel)
    }
}