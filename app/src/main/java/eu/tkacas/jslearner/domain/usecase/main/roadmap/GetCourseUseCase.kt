package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetCourseUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(courseId: String): Course {
        return roadMapRepository.getCourse(courseId)
    }
}