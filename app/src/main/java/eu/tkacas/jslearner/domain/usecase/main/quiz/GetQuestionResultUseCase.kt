package eu.tkacas.jslearner.domain.usecase.main.quiz

import eu.tkacas.jslearner.domain.model.quiz.QuestionResult
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI

class GetQuestionResultUseCase {
    fun execute(question: QuestionUI, userOptions: List<String>): QuestionResult {
        // Convert both lists to sets for comparison
        val correctOptionsSet = question.correctAnswers.toSet()
        val userOptionsSet = userOptions.toSet()

        // Determine the score based on whether the user's selections match the correct answers
        val score = if (userOptionsSet == correctOptionsSet) true else false

        // Determine wrong options: present in userOptions but not in correctAnswers
        val wrongOptions = userOptionsSet.subtract(correctOptionsSet).toList()

        // Return the result
        return QuestionResult(
            correctOptions = question.correctAnswers,
            wrongOptions = wrongOptions,
            isCorrect = score
        )
    }
}