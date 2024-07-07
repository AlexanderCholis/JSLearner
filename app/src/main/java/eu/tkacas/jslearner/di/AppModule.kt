package eu.tkacas.jslearner.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository
import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.domain.usecase.main.GetNavigationDrawerItemsUseCase
import eu.tkacas.jslearner.domain.usecase.main.profile.LoginUseCase
import eu.tkacas.jslearner.domain.usecase.main.profile.SignUpUseCase
import eu.tkacas.jslearner.domain.usecase.main.profile.UpdateUserProfileUseCase
import eu.tkacas.jslearner.domain.usecase.main.profile.UpdateUserStatsUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetRoadMapUseCase
import eu.tkacas.jslearner.domain.usecase.main.profile.GetProfileCompletionUseCase
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
    val updateUserProfileUseCase: UpdateUserProfileUseCase
    val updateUserStatsUseCase: UpdateUserStatsUseCase
    val getProfileCompletionUseCase: GetProfileCompletionUseCase

    // For the SignIn and SignUp screens
    val validateFirstName: ValidateFirstName
    val validateLastName: ValidateLastName
    val validateEmail: ValidateEmail
    val validatePassword: ValidatePassword
    val validateTerms: ValidateTerms


    // For the RoadMapScreen
    val roadMapRepository: RoadMapRepository
    val getRoadMapUseCase: GetRoadMapUseCase

    // For the ExploringPathScreen
    val exploringPathRepository: ExploringPathRepository
    val getCoursesBasedOnExperienceUseCase: GetCoursesBasedOnExperienceUseCase

    // For the NavigationDrawer
    val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase
}