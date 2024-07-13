package eu.tkacas.jslearner.data.model

import com.google.firebase.firestore.PropertyName

data class Question(
    @PropertyName("question_type") val questionType: QuestionType? = null,
    @PropertyName("hint") val hint: String = "",
    @PropertyName("question_description") val questionDescription: String = "",
    @PropertyName("options") var options: List<String> = emptyList(),
    @PropertyName("correct_answers") val correctAnswers: List<String> = emptyList()
)