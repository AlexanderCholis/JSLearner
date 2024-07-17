package eu.tkacas.jslearner.domain.model.quiz

data class QuestionResult(
    val correctOptions: List<String>,
    val wrongOptions: List<String>,
    val score: Boolean
)
