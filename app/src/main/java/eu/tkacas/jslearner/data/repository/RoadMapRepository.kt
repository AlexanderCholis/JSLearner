package eu.tkacas.jslearner.data.repository

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.data.source.remote.RoadMapDataSource

class RoadMapRepository(private val dataSource: RoadMapDataSource) {

    fun getCourses(callback: (List<Course>) -> Unit) {
        dataSource.getCourses(callback)
    }

    fun getLessons(courseId: String, callback: (List<Lesson>) -> Unit) {
        dataSource.getLessons(courseId, callback)
    }

    fun getQuestions(courseId: String, lessonId: String, callback: (List<Question>) -> Unit) {
        dataSource.getQuestions(courseId, lessonId, callback)
    }
}
