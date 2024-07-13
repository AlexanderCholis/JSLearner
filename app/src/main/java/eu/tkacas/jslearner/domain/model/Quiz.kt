package eu.tkacas.jslearner.domain.model

data class Quiz(
    val questions: List<QuestionUI>,
    val score: Int
)
