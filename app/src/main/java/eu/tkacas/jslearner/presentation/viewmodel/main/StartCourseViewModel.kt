package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetCourseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StartCourseViewModel(
    private val getCourseUseCase: GetCourseUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<Course>>(Result.Loading)
    val uiState: StateFlow<Result<Course>> = _uiState.asStateFlow()

   fun loadCourse(courseId: String) {
       viewModelScope.launch(Dispatchers.IO) {
           try {
               _uiState.value = Result.Loading
                val course = getCourseUseCase.execute(courseId)
                _uiState.value = Result.Success(course)
           } catch (e: Exception) {
               _uiState.value = Result.Error(e)
           }
       }
   }
}