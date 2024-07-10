package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainSharedViewModel: ViewModel() {
    private val _selectedCourseId = MutableLiveData<String>()
    val selectedCourseId = _selectedCourseId

    private val _selectedLessonId = MutableLiveData<String>()
    val selectedLessonId = _selectedLessonId

    private val _selectedQuizId = MutableLiveData<String>()
    val selectedQuizId = _selectedQuizId

    fun setSelectedCourseId(courseId: String) {
        _selectedCourseId.value = courseId
    }

    fun setSelectedLessonId(lessonId: String) {
        _selectedLessonId.value = lessonId
    }

    fun setSelectedQuizId(quizId: String) {
        _selectedQuizId.value = quizId
    }
}