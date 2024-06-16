package eu.tkacas.jslearner.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.Question
import eu.tkacas.jslearner.domain.entity.roadmap.RoadMapNodeState
import kotlinx.coroutines.tasks.await

class RoadMapDataSource {

    private val db = FirebaseFirestore.getInstance()

    fun getCourses(callback: (List<Course>) -> Unit) {
        db.collection("courses")
            .get()
            .addOnSuccessListener { result ->
                val courses = result.map { document ->
                    document.toObject(Course::class.java).copy(id = document.id)
                }
                callback(courses)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                callback(emptyList())
            }
    }

    fun getLessons(courseId: String, callback: (List<Lesson>) -> Unit) {
        db.collection("courses").document(courseId).collection("lessons")
            .get()
            .addOnSuccessListener { result ->
                val lessons = result.map { document ->
                    document.toObject(Lesson::class.java).copy(id = document.id)
                }
                callback(lessons)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                callback(emptyList())
            }
    }

    fun getQuestions(courseId: String, lessonId: String, callback: (List<Question>) -> Unit) {
        db.collection("courses").document(courseId).collection("lessons").document(lessonId).collection("questions")
            .get()
            .addOnSuccessListener { result ->
                val questions = result.map { document ->
                    document.toObject(Question::class.java).copy(id = document.id)
                }
                callback(questions)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
                callback(emptyList())
            }
    }
}

