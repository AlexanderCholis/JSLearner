package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.data.model.Lesson

class GetLessonUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(courseId: String, lessonId: String): Lesson {
        return roadMapRepository.getLesson(courseId, lessonId)
    }
}