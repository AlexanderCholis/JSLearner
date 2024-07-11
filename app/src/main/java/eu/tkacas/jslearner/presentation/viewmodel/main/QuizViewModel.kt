package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetQuestionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class QuizViewModel (
    getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<Quiz>>(Result.Loading)
    val uiState: StateFlow<Result<Quiz>> = _uiState //TODO: Add Quiz model

    fun loadQuiz(id: String) { //TODO: Retrieve Quiz Model

    }
}