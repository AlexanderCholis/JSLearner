package eu.tkacas.jslearner.data.model

import com.google.firebase.firestore.PropertyName
import eu.tkacas.jslearner.data.model.CourseLevel

data class Course(
    val id: String = "",
    val level: CourseLevel = CourseLevel.BEGINNER,
    val title: String = "",
    @get:PropertyName("description_short") @set:PropertyName("description_short") var descriptionShort: String = "",
    @get:PropertyName("description_long") @set:PropertyName("description_long") var descriptionLong: String = ""
)