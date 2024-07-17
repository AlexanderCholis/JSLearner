package eu.tkacas.jslearner.domain.model.quiz

data class QuizResults(
    val questionResults: List<QuestionResult>,
    val score: Int
)
