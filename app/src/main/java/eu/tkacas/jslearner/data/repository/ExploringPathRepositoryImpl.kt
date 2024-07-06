package eu.tkacas.jslearner.data.repository

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.CourseLevel
import eu.tkacas.jslearner.data.source.remote.FirestoreDataSource
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository

class ExploringPathRepositoryImpl(private val dataSource: FirestoreDataSource) : ExploringPathRepository {
    override suspend fun getCoursesBasedOnExperience(experienceLevel: ExperienceLevel): List<Course> {
        return when (experienceLevel) {
                ExperienceLevel.NO_EXPERIENCE -> dataSource.getCoursesBasedOnLevel(CourseLevel.BEGINNER)
                ExperienceLevel.SOME_EXPERIENCE -> dataSource.getCoursesBasedOnLevel(CourseLevel.INTERMEDIATE)
                ExperienceLevel.A_LOT_OF_EXPERIENCE -> dataSource.getCoursesBasedOnLevel(CourseLevel.ADVANCED)
            }
    }
}