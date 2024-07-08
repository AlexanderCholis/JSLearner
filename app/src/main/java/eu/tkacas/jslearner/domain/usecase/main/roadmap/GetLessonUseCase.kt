package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.data.model.Lesson

class GetLessonUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(courseId: String): List<Lesson> {
        return roadMapRepository.getLessons(courseId)
    }
}