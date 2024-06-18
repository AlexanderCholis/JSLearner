package eu.tkacas.jslearner.data.model

data class Lesson(
    val id: String = "",
    val title: String = "",
    val theoriesList: List<String> = emptyList(),
    val extraInfo: String = ""
)