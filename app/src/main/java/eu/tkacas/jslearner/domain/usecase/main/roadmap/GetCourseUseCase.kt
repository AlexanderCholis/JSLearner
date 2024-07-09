package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.data.model.Course

class GetCourseUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(courseId: String): Course {
        return roadMapRepository.getCourse(courseId)
    }
}