package eu.tkacas.jslearner.domain.repository

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.Question

interface RoadMapRepository {
    suspend fun getCourses() : List<Course>
    suspend fun getLessons(courseId: String): List<Lesson>
    suspend fun getQuestions(courseId: String, lessonId: String) : List<Question>
    suspend fun getUserCompletedCourses(userId: String): Map<String, List<String>>
}
