package eu.tkacas.jslearner.data.model

data class Course(
    val id: String = "",
    val level: CourseLevel = CourseLevel.BEGINNER,
    val title: String = "",
    val descriptionShort: String = "",
    val descriptionLong: String = ""
)