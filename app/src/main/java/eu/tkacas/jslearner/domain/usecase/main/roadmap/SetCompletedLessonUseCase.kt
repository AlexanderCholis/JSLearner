package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.domain.repository.AuthRepository

class SetCompletedLessonUseCase(private val authRepository: AuthRepository) {
    suspend fun execute(lessonId: String) {
        authRepository.setCompletedLesson(lessonId)
    }
}