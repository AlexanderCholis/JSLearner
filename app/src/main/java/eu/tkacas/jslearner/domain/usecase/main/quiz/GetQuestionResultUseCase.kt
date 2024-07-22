package eu.tkacas.jslearner.domain.usecase.main.quiz

import eu.tkacas.jslearner.domain.model.quiz.QuestionResult
import eu.tkacas.jslearner.domain.model.quiz.QuestionUI

class GetQuestionResultUseCase {
    fun execute(question: QuestionUI, userOptions: List<String>): QuestionResult {
        val correctOptionsSet = question.correctAnswers.toSet()
        val userOptionsSet = userOptions.toSet()

        val score = userOptionsSet == correctOptionsSet

        val wrongOptions = userOptionsSet.subtract(correctOptionsSet).toList()

        return QuestionResult(
            correctOptions = question.correctAnswers,
            wrongOptions = wrongOptions,
            isCorrect = score
        )
    }
}