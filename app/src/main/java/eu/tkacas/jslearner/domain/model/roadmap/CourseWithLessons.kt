package eu.tkacas.jslearner.domain.model.roadmap

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson

data class CourseWithLessons(
    val course: Course,
    val lessons: List<Lesson>
)