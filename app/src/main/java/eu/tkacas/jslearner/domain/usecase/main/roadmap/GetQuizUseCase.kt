package eu.tkacas.jslearner.domain.usecase.main.roadmap

import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.domain.model.QuestionUI
import eu.tkacas.jslearner.domain.model.Quiz
import eu.tkacas.jslearner.domain.repository.RoadMapRepository

class GetQuizUseCase(private val roadMapRepository: RoadMapRepository) {
    suspend fun execute(lessonId: String): Quiz {
        val courseId = lessonId.substring(0, 2)
        val questions = roadMapRepository.getQuestions(courseId, lessonId)
        // Convert to List<QuestionUI> assuming a conversion function exists
        val questionsFinal = questions.map { convertToQuestionFinal(it) }
        // Shuffle the questions
        val shuffledQuestions = questionsFinal.shuffled()
        // Shuffle the options for each question
        shuffledQuestions.forEach { question ->
            question.options = question.options.shuffled().toMutableList()
        }
        // Return Quiz with shuffled questions
        return Quiz(shuffledQuestions, 0)
    }

private fun convertToQuestionFinal(question: Question): QuestionUI {
    return QuestionUI(
        questionType = question.questionType,
        hint = question.hint,
        questionDescription = question.questionDescription,
        // Explicitly convert options to MutableList<String>
        options = question.options.toMutableList(),
        correctAnswers = question.correctAnswers
    )
}
}