package eu.tkacas.jslearner.data.model

import com.google.firebase.firestore.PropertyName

data class Question(
    @PropertyName("question_type") val questionType: String = "",
    @PropertyName("hint") val hint: String = "",
    @PropertyName("question_description") val questionDescription: String = "",
    @PropertyName("options") val options: List<Map<String, String>> = emptyList(),
    @PropertyName("correct_answers") val correctAnswers: List<Map<String, String>> = emptyList()
)