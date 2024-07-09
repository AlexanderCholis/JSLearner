package eu.tkacas.jslearner.data.model

import com.google.firebase.firestore.PropertyName

data class Lesson(
    var id: String = "",
    val title: String = "",
    @get:PropertyName("theories_list") @set:PropertyName("theories_list") var theoriesList: List<String> = emptyList(),
    val description: String = "",
    val url : String = ""
)