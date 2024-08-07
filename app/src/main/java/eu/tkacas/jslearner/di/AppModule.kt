package eu.tkacas.jslearner.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.domain.usecase.main.GetNavigationDrawerItemsUseCase
import eu.tkacas.jslearner.domain.usecase.main.quiz.GetQuestionResultUseCase
import eu.tkacas.jslearner.domain.usecase.main.quiz.GetQuizExistanceUseCase
import eu.tkacas.jslearner.domain.usecase.main.quiz.GetQuizResultsUseCase
import eu.tkacas.jslearner.domain.usecase.main.quiz.GetQuizUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetCourseUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetLessonUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetLessonsUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetRoadMapUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.SetCompletedLessonUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetLeaderboardUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetProfileCompletionUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetUserProfileUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetUserStatsUseCase
import eu.tkacas.jslearner.domain.usecase.user.LoginUseCase
import eu.tkacas.jslearner.domain.usecase.user.LogoutUseCase
import eu.tkacas.jslearner.domain.usecase.user.SetUserProfileUseCase
import eu.tkacas.jslearner.domain.usecase.user.SetUserScoreUseCase
import eu.tkacas.jslearner.domain.usecase.user.SetUserStatsUseCase
import eu.tkacas.jslearner.domain.usecase.user.SignUpUseCase
import eu.tkacas.jslearner.domain.usecase.user.UpdateUserProfileUseCase
import eu.tkacas.jslearner.domain.usecase.user.UpdateUserStatsUseCase
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.GetCoursesBasedOnExperienceUseCase
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateTerms

interface AppModule {
    // Repositories
    val authRepository: AuthRepository

    // Firebase
    fun getFirebaseAuth(): FirebaseAuth
    fun getFirebaseDatabase(): FirebaseDatabase
    fun getFirestoreDatabase(): FirebaseFirestore

    // Profile UseCases
    val loginUseCase: LoginUseCase
    val signUpUseCase: SignUpUseCase
    val logoutUseCase: LogoutUseCase
    val setUserProfileUseCase: SetUserProfileUseCase
    val setUserStatsUseCase: SetUserStatsUseCase
    val getUserProfileUseCase: GetUserProfileUseCase
    val getUserStatsUseCase: GetUserStatsUseCase
    val updateUserProfileUseCase: UpdateUserProfileUseCase
    val updateUserStatsUseCase: UpdateUserStatsUseCase
    val getProfileCompletionUseCase: GetProfileCompletionUseCase
    val setUserScoreUseCase: SetUserScoreUseCase
    val setCompletedLessonUseCase: SetCompletedLessonUseCase

    // For the SignIn and SignUp screens
    val validateFirstName: ValidateFirstName
    val validateLastName: ValidateLastName
    val validateEmail: ValidateEmail
    val validatePassword: ValidatePassword
    val validateTerms: ValidateTerms

    // For the RoadMap
    val roadMapRepository: RoadMapRepository
    val getRoadMapUseCase: GetRoadMapUseCase
    val getCourseUseCase: GetCourseUseCase
    val getLessonUseCase: GetLessonUseCase
    val getLessonsUseCase: GetLessonsUseCase
    val getQuizExistanceUseCase: GetQuizExistanceUseCase
    val getQuizUseCase: GetQuizUseCase
    val getQuestionResultUseCase: GetQuestionResultUseCase
    val getQuizResultsUseCase: GetQuizResultsUseCase

    // For the Leaderboard
    val getLeaderboardUseCase: GetLeaderboardUseCase

    // For the ExploringPathScreen
    val getCoursesBasedOnExperienceUseCase: GetCoursesBasedOnExperienceUseCase

    // For the NavigationDrawer
    val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase
}