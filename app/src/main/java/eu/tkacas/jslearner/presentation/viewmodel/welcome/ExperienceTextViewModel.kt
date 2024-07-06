package eu.tkacas.jslearner.presentation.viewmodel.welcome

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.domain.model.experience.ExperienceLevel
import eu.tkacas.jslearner.presentation.model.ExperienceTextUiItem

class ExperienceTextViewModel: ViewModel() {
    private val noExperienceTexts = listOf(
        ExperienceTextUiItem(
            boldText = R.string.no_experience_no_problem,
            normalText = R.string.any_can_learn_to_code,
            image = R.drawable.element_no_experience
        ),
        ExperienceTextUiItem(
            boldText = R.string.what_is_code,
            normalText = R.string.code_is_how_we_communicate_with_computers,
            image = R.drawable.element_code
        ),
        ExperienceTextUiItem(
            boldText = R.string.what_is_a_programming_language,
            normalText = R.string.a_programming_language_is_used_to_give_computers_instructions,
            image = R.drawable.element_programming
        ),
        ExperienceTextUiItem(
            boldText = R.string.why_should_i_learn_to_code,
            normalText = R.string.perhaps_you_have_an_idea_you_d_like_to_make_a_reality,
            image = R.drawable.element_ideas
        )
    )

    private val someExperienceTexts = listOf(
        ExperienceTextUiItem(
            boldText = R.string.a_new_way_to_learn_to_code,
            normalText = R.string.in_just_5_minutes_or_less_per_day_jslearner_will_help_you_take_the_next_step_in_your_coding_journey,
            image = R.drawable.element_steps
        ),
        ExperienceTextUiItem(
            boldText = R.string.what_is_javascript,
            normalText = R.string.jslearner_teaches_javascript_short,
            image = R.drawable.element_javascript
        ),
        ExperienceTextUiItem(
            boldText = R.string.what_can_i_do_with_javascript,
            normalText = R.string.javascript_is_valued_for_its_versatility,
            image = R.drawable.element_questioning
        ),
        ExperienceTextUiItem(
            boldText = R.string.what_will_jslearner_teach_me,
            normalText = R.string.jslearner_covers_fundamental_coding_concepts_using_fun_bite_size_puzzles,
            image = R.drawable.element_support
        )
    )

    private val aLotOfExperienceTexts = listOf(
        ExperienceTextUiItem(
            boldText = R.string.keep_your_skills_fresh,
            normalText = R.string.in_just_5_minutes_or_less_per_day_jslearner_will_help_you_keep_your_coding_skills_fresh,
            image = R.drawable.element_skills
        ),
        ExperienceTextUiItem(
            boldText = R.string.what_does_jslearner_teach,
            normalText = R.string.jslearner_teaches_javascript_extended,
            image = R.drawable.element_teaching
        ),
        ExperienceTextUiItem(
            boldText = R.string.why_use_jslearner,
            normalText = R.string.in_5_minutes_or_less_per_day_jslearner_will_keep_your_coding_skills_sharp,
            image = R.drawable.element_application
        ),
        ExperienceTextUiItem(
            boldText = R.string.how_much_will_i_learn,
            normalText = R.string.with_jslearner_you_will_practice_fundamental_coding,
            image = R.drawable.element_learn
        )
    )

    fun returnTexts(level: ExperienceLevel): List<ExperienceTextUiItem> {
        return when(level) {
            ExperienceLevel.NO_EXPERIENCE -> noExperienceTexts
            ExperienceLevel.SOME_EXPERIENCE -> someExperienceTexts
            ExperienceLevel.A_LOT_OF_EXPERIENCE -> aLotOfExperienceTexts
        }
    }
}