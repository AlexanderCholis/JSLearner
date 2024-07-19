package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.model.HelpTextUiItem


class HelpViewModel : ViewModel() {
    private val helpTexts = listOf(
        HelpTextUiItem(
            title = R.string.help_levels,
            content = R.string.difficulty_levels
        ),
//        HelpTextUiItem(
//            title = R.string.help_title_2,
//            content = R.string.help_content_2
//        )
        // Add more help texts as needed
    )

    fun getHelpTexts() = helpTexts
}