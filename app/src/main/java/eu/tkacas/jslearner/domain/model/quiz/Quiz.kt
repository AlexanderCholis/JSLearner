package eu.tkacas.jslearner.domain.model.quiz

data class Quiz(
    val questions: List<QuestionUI>,
    var score: Int
)
