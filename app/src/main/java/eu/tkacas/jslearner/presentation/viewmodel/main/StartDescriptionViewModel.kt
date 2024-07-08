package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetCourseUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetLessonUseCase
import kotlinx.coroutines.launch

class StartDescriptionViewModel(
    private val getCourseUseCase: GetCourseUseCase,
    private val getLessonUseCase: GetLessonUseCase
) : ViewModel() {

    private val _courseDetails = MutableLiveData<Result<Course>>()
    val courseDetails: LiveData<Result<Course>> = _courseDetails

    private val _lessonDetails = MutableLiveData<Result<Lesson>>()
    val lessonDetails: LiveData<Result<Lesson>> = _lessonDetails

    fun loadDetails(id: String) {
        viewModelScope.launch {
            when (id.length) {
                2 -> loadCourseDetails(id)
                4 -> loadLessonDetails(id)
                else -> {
                    _courseDetails.value = Result.Error(Exception("Invalid ID"))
                    _lessonDetails.value = Result.Error(Exception("Invalid ID"))
                }
            }
        }
    }

    private suspend fun loadCourseDetails(id: String) {
        try {
            val course = getCourseUseCase.execute(id)
            _courseDetails.postValue(Result.Success(course))
        } catch (e: Exception) {
            _courseDetails.postValue(Result.Error(e))
        }
        _lessonDetails.postValue(Result.Loading) // Indicate that lesson details are being reset
    }

    private suspend fun loadLessonDetails(id: String) {
        if (id.length == 4) {
            val courseId = id.substring(0, 2)
            val lessonId = id
            try {
                val lesson = getLessonUseCase.execute(courseId, lessonId)
                _lessonDetails.postValue(Result.Success(lesson))
            } catch (e: Exception) {
                _lessonDetails.postValue(Result.Error(e))
            }
        } else {
            _lessonDetails.postValue(Result.Error(Exception("Invalid lesson ID")))
        }
        _courseDetails.postValue(Result.Loading) // Indicate that course details are being reset
    }
}