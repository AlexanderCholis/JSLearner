package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState

class MainSharedViewModel : ViewModel() {
    private val _selectedCourseId = MutableLiveData<String>()
    val selectedCourseId = _selectedCourseId

    private val _selectedLessonId = MutableLiveData<String>()
    val selectedLessonId = _selectedLessonId

    private val _selectedQuizId = MutableLiveData<String>()
    val selectedQuizId = _selectedQuizId

    private val _user = MutableLiveData<User?>()
    val user = _user

    private val _coursesState = MutableLiveData<List<RoadMapNodeState>>()
    val coursesState = _coursesState

    fun setSelectedCourseId(courseId: String) {
        _selectedCourseId.value = courseId
    }

    fun setSelectedLessonId(lessonId: String) {
        _selectedLessonId.value = lessonId
    }

    fun setSelectedQuizId(quizId: String) {
        _selectedQuizId.value = quizId
    }

    fun setUser(user: User?) {
        _user.value = user
    }

    fun setCoursesState(coursesState: List<RoadMapNodeState>) {
        _coursesState.value = coursesState
    }
}