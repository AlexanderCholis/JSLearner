package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.domain.model.quiz.Quiz
import eu.tkacas.jslearner.domain.model.quiz.QuizResults
import eu.tkacas.jslearner.domain.usecase.main.quiz.GetQuizResultsUseCase

class QuizViewModel(
    private val getQuizResultsUseCase: GetQuizResultsUseCase
) : ViewModel() {
    fun getQuizResults(quiz: Quiz, userOptions: List<List<String>>): QuizResults {
        return getQuizResultsUseCase.execute(quiz, userOptions)
    }
}