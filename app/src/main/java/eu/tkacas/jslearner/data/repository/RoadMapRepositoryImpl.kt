package eu.tkacas.jslearner.data.repository

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.data.source.remote.FirestoreDataSource
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class RoadMapRepositoryImpl(private val dataSource: FirestoreDataSource) : RoadMapRepository {

    override suspend fun getCourses() : List<Course> {
        return dataSource.getCourses()
    }

    override suspend fun getCourse(courseId: String): Course {
        return dataSource.getCourse(courseId)
    }

    override suspend fun getLessons(courseId: String): List<Lesson> {
        return dataSource.getLessons(courseId)
    }

    override suspend fun getLesson(courseId: String, lessonId: String): Lesson {
        return dataSource.getLesson(courseId, lessonId)
    }

    override suspend fun getQuestions(courseId: String, lessonId: String) : List<Question> {
        return dataSource.getQuestions(courseId, lessonId)
    }

    override suspend fun getUserCompletedCourses(userId: String): Map<String, List<String>> {
        return dataSource.getUserCompletedCourses(userId)
    }
}