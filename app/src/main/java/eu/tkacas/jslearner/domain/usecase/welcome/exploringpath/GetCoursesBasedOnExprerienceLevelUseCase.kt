package eu.tkacas.jslearner.domain.usecase.welcome.exploringpath

import eu.tkacas.jslearner.data.model.CourseLevel
import eu.tkacas.jslearner.domain.model.CourseShort
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetCoursesBasedOnExperienceUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(experienceLevel: ExperienceLevel): MutableList<CourseShort> {

        val courses = when (experienceLevel) {
            ExperienceLevel.NO_EXPERIENCE -> roadMapRepository.getCoursesBasedOnLevel(CourseLevel.BEGINNER)
            ExperienceLevel.SOME_EXPERIENCE -> roadMapRepository.getCoursesBasedOnLevel(CourseLevel.INTERMEDIATE)
            ExperienceLevel.A_LOT_OF_EXPERIENCE -> roadMapRepository.getCoursesBasedOnLevel(CourseLevel.ADVANCED)
        }

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