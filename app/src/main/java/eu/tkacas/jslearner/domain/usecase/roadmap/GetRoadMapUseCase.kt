package eu.tkacas.jslearner.domain.usecase.roadmap

import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeStatus
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetRoadMapUseCase(private val repository: RoadMapRepository) {

    suspend fun execute(userId: String): List<RoadMapNodeState> {
        val experienceLevel = "beginner" // TODO: get user experience level from repository

        val courses = repository.getCourses()
        val completedCourses = repository.getUserCompletedCourses(userId)
        val roadMapNodes = mutableListOf<RoadMapNodeState>()

        for ((index, course) in courses.withIndex()) {
            val lessons = repository.getLessons(course.id)
            val isCourseCompleted = lessons.all { lesson -> completedCourses[course.id]?.contains(lesson.id) == true }
            val courseStatus = when {
                isCourseCompleted -> RoadMapNodeStatus.COMPLETED
                else -> RoadMapNodeStatus.IN_PROGRESS // or other logic to determine LOCKED status
            }
            roadMapNodes.add(RoadMapNodeState(courseStatus, RoadMapNodePosition.MIDDLE, course.title))

            for (lesson in lessons) {
                val isLessonCompleted = completedCourses[course.id]?.contains(lesson.id) == true
                val lessonStatus = when {
                    isLessonCompleted -> RoadMapNodeStatus.COMPLETED
                    else -> RoadMapNodeStatus.LOCKED // or other logic to determine LOCKED status
                }
                val position = if (index == courses.lastIndex && lesson == lessons.last()) {
                    RoadMapNodePosition.LAST
                } else {
                    RoadMapNodePosition.MIDDLE
                }
                roadMapNodes.add(RoadMapNodeState(lessonStatus, position, lesson.title))
            }
        }

        return roadMapNodes
    }
}