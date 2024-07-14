package eu.tkacas.jslearner.domain.model.quiz

data class QuizResult(
    val questionResults: List<QuestionResult>,
    val score: Int
)
