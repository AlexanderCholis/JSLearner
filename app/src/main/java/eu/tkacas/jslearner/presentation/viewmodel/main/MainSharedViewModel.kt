package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.domain.model.User
import eu.tkacas.jslearner.domain.model.quiz.Quiz
import eu.tkacas.jslearner.domain.model.roadmap.RoadMapNodeState

class MainSharedViewModel : ViewModel() {
    private val _selectedCourseId = MutableLiveData<String>()
    val selectedCourseId = _selectedCourseId

    private val _selectedLessonId = MutableLiveData<String>()
    val selectedLessonId = _selectedLessonId

    private val _selectedLesson = MutableLiveData<Lesson>()
    val selectedLesson = _selectedLesson

    private val _selectedQuiz = MutableLiveData<Quiz>()
    val selectedQuiz = _selectedQuiz

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

    fun setSelectedLesson(lesson: Lesson) {
        _selectedLesson.value = lesson
    }

    fun setSelectedQuiz(quiz: Quiz) {
        _selectedQuiz.value = quiz
    }

    fun setUser(user: User?) {
        _user.value = user
    }

    fun setCoursesState(coursesState: List<RoadMapNodeState>) {
        _coursesState.value = coursesState
    }
}