package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetQuestionsUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(courseId: String, lessonId: String): List<Question> {
        return roadMapRepository.getQuestions(courseId, lessonId)
    }
}