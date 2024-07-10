package eu.tkacas.jslearner.presentation.viewmodel.main

import androidx.lifecycle.ViewModel
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetQuestionsUseCase

class QuizViewModel (
    getQuestionsUseCase: GetQuestionsUseCase
) : ViewModel() {
}