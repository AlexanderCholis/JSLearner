package eu.tkacas.jslearner.data.model

data class Question(
    val id: String = "",
    val questionType: String = "",
    val hint: String = "",
    val questionDescription: String = "",
    val options: List<String> = emptyList(),
    val correctAnswers: List<String> = emptyList()
)