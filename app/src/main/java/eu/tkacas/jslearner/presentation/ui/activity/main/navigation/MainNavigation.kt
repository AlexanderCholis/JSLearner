package eu.tkacas.jslearner.presentation.ui.activity.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.tkacas.jslearner.data.model.Lesson
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.AccountScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.LeaderboardScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.LessonScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.RoadMapScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.SettingsScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.StartCourseScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.StartLessonScreen
import eu.tkacas.jslearner.presentation.viewmodel.main.LessonViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.RoadMapViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartCourseViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartLessonViewModel

@Composable
internal fun MainNavigation(
    roadMapViewModel: RoadMapViewModel,
    startCourseViewModel: StartCourseViewModel,
    startLessonViewModel: StartLessonViewModel,
    lessonViewModel: LessonViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController,
        startDestination = "roadmap"
    ) {
        composable("roadmap") {
            RoadMapScreen(
                navController = navController,
                viewModel = roadMapViewModel
            )
        }
        composable("startCourse?courseId={courseId}") { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
            StartCourseScreen(
                navController = navController,
                viewModel = startCourseViewModel,
                id = courseId
            )
        }
        composable("startLesson?lessonId={lessonId}") { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: ""
            StartLessonScreen(
                navController = navController,
                viewModel = startLessonViewModel,
                id = lessonId
            )
        }
        composable("lesson?lessonId={lessonId}") { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: ""
            LessonScreen(
                navController = navController,
                viewModel = lessonViewModel,
                id = lessonId
            )
        }
        composable("account") {
            AccountScreen(
                navController = navController
            )
        }
        composable("settings") {
            SettingsScreen(
                navController = navController
            )
        }
        composable("leaderboard") {
            LeaderboardScreen(
                navController = navController
            )
        }
    }

}