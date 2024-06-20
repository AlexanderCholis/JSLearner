package eu.tkacas.jslearner.domain.repository

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel

interface ExploringPathRepository {
    suspend fun getCoursesBasedOnExperience(experienceLevel: ExperienceLevel): List<Course>
}