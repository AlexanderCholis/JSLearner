package eu.tkacas.jslearner.domain.usecase.main.quiz

import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI
import eu.tkacas.jslearner.domain.model.quiz.Quiz
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetQuizUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(lessonId: String): Quiz {
        val courseId = lessonId.substring(0, 2)
        val questions = roadMapRepository.getQuestions(courseId, lessonId)
        val questionsFinal = questions.map { convertToQuestionFinal(it) }
        val shuffledQuestions = questionsFinal.shuffled()
        shuffledQuestions.forEach { question ->
            question.options = question.options.shuffled().toMutableList()
        }
        return Quiz(shuffledQuestions, 0)
    }

    private fun convertToQuestionFinal(question: Question): QuestionUI {
        return QuestionUI(
            questionType = question.questionType,
            hint = question.hint,
            questionDescription = question.questionDescription,
            options = question.options.toMutableList(),
            correctAnswers = question.correctAnswers
        )
    }
}