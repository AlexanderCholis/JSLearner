package eu.tkacas.jslearner.data.repository

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.data.source.remote.RoadMapDataSource
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class RoadMapRepositoryImpl(private val dataSource: RoadMapDataSource) : RoadMapRepository {

    override suspend fun getCourses() : List<Course> {
        return dataSource.getCourses()
    }

    override suspend fun getLessons(courseId: String): List<Lesson> {
        return dataSource.getLessons(courseId)
    }

    override suspend fun getQuestions(courseId: String, lessonId: String) : List<Question> {
        return dataSource.getQuestions(courseId, lessonId)
    }

    override suspend fun getUserCompletedCourses(userId: String): Map<String, List<String>> {
        return dataSource.getUserCompletedCourses(userId)
    }
}