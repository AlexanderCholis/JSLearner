package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.model.roadmap.CourseWithLessons
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetCourseUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetLessonsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartCourseViewModel(
    private val getCourseUseCase: GetCourseUseCase,
    private val getLessonsUseCase: GetLessonsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<CourseWithLessons>>(Result.Loading)
    val uiState: StateFlow<Result<CourseWithLessons>> = _uiState.asStateFlow()

    fun loadCourse(courseId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = Result.Loading
                val course = getCourseUseCase.execute(courseId)
                val lessons = getLessonsUseCase.execute(courseId)
                _uiState.value = Result.Success(CourseWithLessons(course, lessons))
            } catch (e: Exception) {
                _uiState.value = Result.Error(e)
            }
        }
    }
}