package eu.tkacas.jslearner.domain.model

import eu.tkacas.jslearner.data.model.Question

data class Quiz(
    val questions: List<Question>,
    val score: Int
)
