package eu.tkacas.jslearner.presentation.ui.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.tkacas.jslearner.JSLearner
import eu.tkacas.jslearner.presentation.ui.activity.main.navigation.MainNavigation
import eu.tkacas.jslearner.presentation.ui.theme.JSLearnerTheme
import eu.tkacas.jslearner.presentation.viewmodel.main.RoadMapViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartCourseViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartLessonViewModel
import eu.tkacas.jslearner.presentation.viewmodel.viewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JSLearnerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val roadMapViewModel = viewModel<RoadMapViewModel>(
                        factory = viewModelFactory {
                            RoadMapViewModel(
                                getRoadMapUseCase = JSLearner.appModule.getRoadMapUseCase,
                                authRepository = JSLearner.appModule.authRepository
                            )
                        }
                    )
                    val startCourseViewModel = viewModel<StartCourseViewModel>(
                        factory = viewModelFactory {
                            StartCourseViewModel(
                                getCourseUseCase = JSLearner.appModule.getCourseUseCase,
                            )
                        }
                    )
                    val startLessonViewModel = viewModel<StartLessonViewModel>(
                        factory = viewModelFactory {
                            StartLessonViewModel(
                                getLessonUseCase = JSLearner.appModule.getLessonUseCase,
                            )
                        }
                    )

                    MainNavigation(
                        roadMapViewModel = roadMapViewModel,
                        startCourseViewModel = startCourseViewModel,
                        startLessonViewModel = startLessonViewModel
                    )
                }
            }
        }
    }
}