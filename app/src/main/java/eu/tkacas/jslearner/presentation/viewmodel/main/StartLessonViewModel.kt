package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetLessonUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartLessonViewModel(
    private val getLessonUseCase: GetLessonUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<Lesson>>(Result.Loading)
    val uiState: StateFlow<Result<Lesson>> = _uiState.asStateFlow()

    fun loadLesson(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val courseId = id.substring(0, 2)
            val lessonId = id
            try {
                _uiState.value = Result.Loading
                val lesson = getLessonUseCase.execute(courseId, lessonId)
                _uiState.value = Result.Success(lesson)
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }
}