package eu.tkacas.jslearner.domain.usecase.main.quiz

import eu.tkacas.jslearner.domain.model.quiz.Quiz
import eu.tkacas.jslearner.domain.model.quiz.QuizResults

class GetQuizResultsUseCase(private val getQuestionResultUseCase: GetQuestionResultUseCase) {

    fun execute(quiz: Quiz, userOptions: List<List<String>>): QuizResults {
        val questionResults = quiz.questions.mapIndexed { index, question ->
            // Retrieve user-selected options for the current question using index
            val userSelectedOptions = userOptions.getOrNull(index) ?: emptyList()
            getQuestionResultUseCase.execute(question, userSelectedOptions)
        }
        val score = questionResults.sumOf { if (it.isCorrect) 10 else -5 as Int }
        return QuizResults(questionResults, score)
    }
}