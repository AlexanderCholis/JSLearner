package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.entity.experience.ExperienceLevel
import eu.tkacas.jslearner.domain.entity.experience.ExperienceLevelItem
import eu.tkacas.jslearner.domain.entity.experience.ExperienceLevelUiItem

class ExperienceLevelViewModel: ViewModel() {
    private val levels = listOf(
        ExperienceLevelItem(ExperienceLevel.NO_EXPERIENCE),
        ExperienceLevelItem(ExperienceLevel.SOME_EXPERIENCE),
        ExperienceLevelItem(ExperienceLevel.A_LOT_OF_EXPERIENCE)
    )

    val uiLevels: List<ExperienceLevelUiItem> = levels.map {
        when (it.level) {
            ExperienceLevel.NO_EXPERIENCE -> ExperienceLevelUiItem(R.drawable.star_1, R.string.no_experience, it.level)
            ExperienceLevel.SOME_EXPERIENCE -> ExperienceLevelUiItem(R.drawable.stars_2, R.string.some_experience, it.level)
            ExperienceLevel.A_LOT_OF_EXPERIENCE -> ExperienceLevelUiItem(R.drawable.stars_3, R.string.a_lot_of_experience, it.level)
        }
    }
}