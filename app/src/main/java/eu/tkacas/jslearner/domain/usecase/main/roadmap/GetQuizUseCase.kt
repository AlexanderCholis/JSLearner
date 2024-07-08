package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.data.model.Question

class GetQuizUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(courseId: String, lessonId: String): List<Question> {
        return roadMapRepository.getQuestions(courseId, lessonId)
    }
}