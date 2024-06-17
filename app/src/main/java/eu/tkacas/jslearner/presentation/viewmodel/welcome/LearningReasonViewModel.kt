package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.entity.learningreason.LearningReason
import eu.tkacas.jslearner.presentation.model.LearningReasonUiItem

class LearningReasonViewModel: ViewModel() {
    fun returnReasonsList(): List<LearningReasonUiItem> {
        return listOf(
            LearningReasonUiItem(
                image = R.drawable.career,
                text = R.string.gain_skills,
                reason = LearningReason.GAIN_SKILLS
            ),
            LearningReasonUiItem(
                image = R.drawable.work,
                text = R.string.change_career,
                reason = LearningReason.CHANGE_CAREER
            ),
            LearningReasonUiItem(
                image = R.drawable.`fun`,
                text = R.string.just_for_fun,
                reason = LearningReason.JUST_FOR_FUN
            ),
            LearningReasonUiItem(
                image = R.drawable.games,
                text = R.string.develop_games,
                reason = LearningReason.DEVELOP_GAMES
            ),
            LearningReasonUiItem(
                image = R.drawable.application,
                text = R.string.build_an_application_or_website,
                reason = LearningReason.BUILD_APP_OR_WEBSITE
            )
        )
    }
}