package eu.tkacas.jslearner.domain.usecase.roadmap

import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodePosition
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeStatus
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetRoadMapUseCase(private val repository: RoadMapRepository) {

    suspend fun execute(userId: String): List<RoadMapNodeState> {
        val courses = repository.getCourses()
        val completedCourses = repository.getUserCompletedCourses(userId)
        val roadMapNodes = mutableListOf<RoadMapNodeState>()

        for (course in courses) {
            val lessons = repository.getLessons(course.id)
            for (lesson in lessons) {
                val isCompleted = completedCourses[course.id]?.contains(lesson.id) == true
                val status = when {
                    isCompleted -> RoadMapNodeStatus.COMPLETED
                    else -> RoadMapNodeStatus.IN_PROGRESS // or other logic to determine LOCKED status
                }
                roadMapNodes.add(RoadMapNodeState(status, RoadMapNodePosition.MIDDLE, lesson.title))
            }
        }

        return roadMapNodes
    }
}
