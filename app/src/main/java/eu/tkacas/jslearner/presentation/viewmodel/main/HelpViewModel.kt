package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.model.HelpTextUiItem


class HelpViewModel : ViewModel() {
    private val helpTexts = listOf(
        HelpTextUiItem(
            title = R.string.help_levels,
            content = R.string.help_difficulty_levels,
            image = R.drawable.element_difficulty_levels
        ),
        HelpTextUiItem(
            title = R.string.help_self_assessment,
            content = R.string.help_quiz,
            image = R.drawable.element_quiz_asnwering
        ),
        HelpTextUiItem(
            title = R.string.help_progress_monitoring,
            content = R.string.help_points,
            image = R.drawable.element_leaderboard
        ),
        HelpTextUiItem(
            title = R.string.help_points_system,
            content = R.string.help_points_system_description,
            image = R.drawable.element_processing_thoughts
        ),
    )

    fun getHelpTexts() = helpTexts
}