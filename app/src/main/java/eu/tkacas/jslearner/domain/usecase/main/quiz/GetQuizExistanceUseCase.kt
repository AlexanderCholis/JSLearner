package eu.tkacas.jslearner.domain.usecase.main.quiz

import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetQuizExistanceUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(lessonId: String): Boolean {
        val courseId = lessonId.substring(0, 2)
        val questions = roadMapRepository.getQuestions(courseId, lessonId)
        return questions.isNotEmpty()
    }
}