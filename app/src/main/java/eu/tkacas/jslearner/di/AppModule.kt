package eu.tkacas.jslearner.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository
import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetRoadMapUseCase
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.GetCoursesBasedOnExperienceUseCase
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.UpdateUserProfileUseCase
import eu.tkacas.jslearner.domain.usecase.welcome.exploringpath.UpdateUserStatsUseCase
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.welcome.validateregex.ValidateTerms

interface AppModule {

    // Firebase
    fun getFirebaseAuth(): FirebaseAuth
    fun getFirebaseDatabase(): FirebaseDatabase
    fun getFirestoreDatabase(): FirebaseFirestore

    // For the SignIn and SignUp screens
    val authRepository: AuthRepository
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
    val updateUserProfileUseCase: UpdateUserProfileUseCase
    val updateUserStatsUseCase: UpdateUserStatsUseCase
}