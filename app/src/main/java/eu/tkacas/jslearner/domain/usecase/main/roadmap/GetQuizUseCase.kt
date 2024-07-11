package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.domain.model.Quiz
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetQuizUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(lessonId: String): Quiz {
        val courseId = lessonId.substring(0, 2)
        val questions = roadMapRepository.getQuestions(courseId, lessonId)
        return Quiz(questions, 0)
    }
}