package eu.tkacas.jslearner.domain.model.quiz

import eu.tkacas.jslearner.data.model.QuestionType

data class QuestionUI(
    val questionType: QuestionType? = null,
    val hint: String = "",
    val questionDescription: String = "",
    var options: MutableList<String> = mutableListOf(),
    val correctAnswers: List<String> = emptyList()
)