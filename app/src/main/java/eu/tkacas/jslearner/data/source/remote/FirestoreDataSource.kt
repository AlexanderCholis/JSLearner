package eu.tkacas.jslearner.data.source.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import eu.tkacas.jslearner.data.model.Course
import eu.tkacas.jslearner.data.model.CourseLevel
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.data.model.Question
import kotlinx.coroutines.tasks.await

class FirestoreDataSource(private val db: FirebaseFirestore) {

    suspend fun getCourses(): List<Course> {
        return try {
            val result = db.collection("courses").get().await()
            result.map { document ->
                document.toObject<Course>().copy(id = document.id)
            }
        } catch (e: Exception) {
            Log.w("FirestoreDataSource", "Error getting documents.", e)
            emptyList()
        }
    }

    suspend fun getLessons(courseId: String): List<Lesson> {
        return try {
            val result = db.collection("courses").document(courseId).collection("lessons").get().await()
            result.map { document ->
                document.toObject<Lesson>().copy(id = document.id)
            }
        } catch (e: Exception) {
            Log.w("FirestoreDataSource", "Error getting documents.", e)
            emptyList()
        }
    }

    suspend fun getQuestions(courseId: String, lessonId: String): List<Question> {
        return try {
            val result = db.collection("courses").document(courseId)
                .collection("lessons").document(lessonId)
                .collection("questions").get().await()
            result.map { document ->
                document.toObject<Question>().copy(id = document.id)
            }
        } catch (e: Exception) {
            Log.w("FirestoreDataSource", "Error getting documents.", e)
            emptyList()
        }
    }

    suspend fun getUserCompletedCourses(userId: String): Map<String, List<String>> {
        return try {
            val result = db.collection("users").document(userId).collection("done_courses").get().await()
            result.associate { document ->
                document.id to (document["list_of_completed_lessons"] as List<String>)
            }
        } catch (e: Exception) {
            Log.w("FirestoreDataSource", "Error getting documents.", e)
            emptyMap()
        }
    }

    suspend fun getCoursesBasedOnLevel(courseLevel: CourseLevel): List<Course> {
        return try {
            val result = db.collection("courses")
                .whereEqualTo("level", courseLevel.name)
                .get()
                .await()
            result.map { document ->
                document.toObject<Course>().copy(id = document.id)
            }
        } catch (e: Exception) {
            Log.w("FirestoreDataSource", "Error getting documents.", e)
            emptyList()
        }
    }

}
