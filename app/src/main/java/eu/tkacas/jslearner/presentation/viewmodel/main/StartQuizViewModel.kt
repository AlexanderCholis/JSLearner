package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.model.quiz.Quiz
import eu.tkacas.jslearner.domain.usecase.main.quiz.GetQuizUseCase
import eu.tkacas.jslearner.domain.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartQuizViewModel(
    private val getQuizUseCase: GetQuizUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<Quiz>>(Result.Loading)
    val uiState: StateFlow<Result<Quiz>> = _uiState.asStateFlow()

    fun loadQuiz(lessonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = Result.Loading
                val quiz = getQuizUseCase.execute(lessonId)
                _uiState.value = Result.Success(quiz)
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }
}