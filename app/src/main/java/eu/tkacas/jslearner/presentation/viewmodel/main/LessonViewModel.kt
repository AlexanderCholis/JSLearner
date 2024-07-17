package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.usecase.main.quiz.GetQuizExistanceUseCase
import kotlinx.coroutines.launch

class LessonViewModel(
    private val getQuizExistanceUseCase: GetQuizExistanceUseCase
) : ViewModel() {
    fun hasQuizForLesson(lessonId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val hasQuiz = getQuizExistanceUseCase.execute(lessonId)
            onResult(hasQuiz)
        }
    }
}