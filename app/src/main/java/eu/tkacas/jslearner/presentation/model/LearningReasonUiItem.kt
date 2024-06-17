package eu.tkacas.jslearner.presentation.model

import eu.tkacas.jslearner.domain.entity.learningreason.LearningReason

data class LearningReasonUiItem(
    val image: Int,
    val text: Int,
    val reason: LearningReason
)
