package eu.tkacas.jslearner.presentation.ui.activity.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.AboutScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.AccountScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.AnsweredQuestionScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.CoursesPathScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.LeaderboardScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.LessonScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.QuizScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.ResultsScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.RoadMapScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.StartCourseScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.StartLessonScreen
import eu.tkacas.jslearner.presentation.ui.activity.main.screens.StartQuizScreen
import eu.tkacas.jslearner.presentation.viewmodel.main.AccountViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.LessonViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.MainSharedViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.QuizViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.RoadMapViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartCourseViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartLessonViewModel
import eu.tkacas.jslearner.presentation.viewmodel.main.StartQuizViewModel

@Composable
internal fun MainNavigation(
    roadMapViewModel: RoadMapViewModel,
    startCourseViewModel: StartCourseViewModel,
    startLessonViewModel: StartLessonViewModel,
    lessonViewModel: LessonViewModel,
    startQuizViewModel: StartQuizViewModel,
    quizViewModel: QuizViewModel,
    accountViewModel: AccountViewModel
) {
    val navController = rememberNavController()
    val sharedViewModel = MainSharedViewModel()

    NavHost(
        navController = navController,
        startDestination = "roadmap"
    ) {
        composable("roadmap") {
            RoadMapScreen(
                navController = navController,
                viewModel = roadMapViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("startCourse") {
            StartCourseScreen(
                navController = navController,
                viewModel = startCourseViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("startLesson") {
            StartLessonScreen(
                navController = navController,
                viewModel = startLessonViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("lesson") {
            LessonScreen(
                navController = navController,
                viewModel = lessonViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("account") {
            AccountScreen(
                navController = navController,
                viewModel = accountViewModel
            )
        }
        composable("about") {
            AboutScreen(
                navController = navController
            )
        }
        composable("leaderboard") {
            LeaderboardScreen(
                navController = navController,
                viewModel = accountViewModel
            )
        }
        composable("coursesPath") {
            CoursesPathScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        composable("startQuiz") {
            StartQuizScreen(
                navController = navController,
                viewModel = startQuizViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("quiz") {
            QuizScreen(
                navController = navController,
                viewModel = quizViewModel,
                sharedViewModel = sharedViewModel
            )
        }
        composable("results") {
            ResultsScreen(
                navController = navController,
                sharedViewModel = sharedViewModel
            )
        }
        composable(
            route = "answeredQuestion/{questionIndex}",
            arguments = listOf(navArgument("questionIndex") { type = NavType.IntType })
        ) { backStackEntry ->
            val questionIndex = backStackEntry.arguments?.getInt("questionIndex") ?: 0
            AnsweredQuestionScreen(
                navController = navController,
                sharedViewModel = sharedViewModel,
                questionIndex = questionIndex
            )
        }
    }

}