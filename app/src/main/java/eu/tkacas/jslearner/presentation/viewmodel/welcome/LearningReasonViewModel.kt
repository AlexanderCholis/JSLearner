package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.learningreason.LearningReason
import eu.tkacas.jslearner.domain.model.learningreason.LearningReasonItem
import eu.tkacas.jslearner.presentation.model.LearningReasonUiItem

class LearningReasonViewModel : ViewModel() {
    private val learningReasons = listOf(
        LearningReasonItem(LearningReason.GAIN_SKILLS),
        LearningReasonItem(LearningReason.CHANGE_CAREER),
        LearningReasonItem(LearningReason.JUST_FOR_FUN),
        LearningReasonItem(LearningReason.DEVELOP_GAMES),
        LearningReasonItem(LearningReason.BUILD_APP_OR_WEBSITE)
    )

    val uiLearningReasons: List<LearningReasonUiItem> = learningReasons.map {
        when (it.reason) {
            LearningReason.GAIN_SKILLS -> LearningReasonUiItem(
                R.drawable.career,
                R.string.gain_skills,
                it.reason
            )

            LearningReason.CHANGE_CAREER -> LearningReasonUiItem(
                R.drawable.work,
                R.string.change_career,
                it.reason
            )

            LearningReason.JUST_FOR_FUN -> LearningReasonUiItem(
                R.drawable.`fun`,
                R.string.just_for_fun,
                it.reason
            )

            LearningReason.DEVELOP_GAMES -> LearningReasonUiItem(
                R.drawable.games,
                R.string.develop_games,
                it.reason
            )

            LearningReason.BUILD_APP_OR_WEBSITE -> LearningReasonUiItem(
                R.drawable.application,
                R.string.build_an_application_or_website,
                it.reason
            )
        }
    }
}