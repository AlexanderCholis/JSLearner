package eu.tkacas.jslearner.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import eu.tkacas.jslearner.data.repository.AuthRepositoryImpl
import eu.tkacas.jslearner.data.repository.ExploringPathRepositoryImpl
import eu.tkacas.jslearner.data.repository.RoadMapRepositoryImpl
import eu.tkacas.jslearner.data.source.remote.FirebaseDataSource
import eu.tkacas.jslearner.data.source.remote.FirestoreDataSource
import eu.tkacas.jslearner.domain.model.NavigationDrawer
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.repository.ExploringPathRepository
import eu.tkacas.jslearner.domain.repository.RoadMapRepository
import eu.tkacas.jslearner.domain.usecase.main.GetNavigationDrawerItemsUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetCourseUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetLessonUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetLessonsUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetQuestionsUseCase
import eu.tkacas.jslearner.domain.usecase.main.roadmap.GetRoadMapUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetProfileCompletionUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetUserProfileUseCase
import eu.tkacas.jslearner.domain.usecase.user.GetUserStatsUseCase
import eu.tkacas.jslearner.domain.usecase.user.LoginUseCase
import eu.tkacas.jslearner.domain.usecase.user.LogoutUseCase
import eu.tkacas.jslearner.domain.usecase.user.SetUserProfileUseCase
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

class AppModuleImpl(
    private val appContext: Context
) : AppModule {
    // Repositories
    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(firebaseDataSource, firestoreDataSource)
    }

    // Firebase
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun getFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    // Firebase and Firestore
    private val firestoreDataSource = FirestoreDataSource(getFirestoreDatabase())
    private val firebaseDataSource = FirebaseDataSource(getFirebaseAuth(), getFirebaseDatabase())

    override fun getFirestoreDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    // Profile UseCases
    override val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(authRepository)
    }
    override val signUpUseCase: SignUpUseCase by lazy {
        SignUpUseCase(authRepository)
    }
    override val logoutUseCase: LogoutUseCase by lazy {
        LogoutUseCase(authRepository)
    }
    override val setUserProfileUseCase: SetUserProfileUseCase by lazy {
        SetUserProfileUseCase(authRepository)
    }
    override val setUserStatsUseCase: SetUserStatsUseCase by lazy {
        SetUserStatsUseCase(authRepository)
    }
    override val getUserProfileUseCase: GetUserProfileUseCase by lazy {
        GetUserProfileUseCase(authRepository)
    }
    override val getUserStatsUseCase: GetUserStatsUseCase by lazy {
        GetUserStatsUseCase(authRepository)
    }
    override val updateUserProfileUseCase: UpdateUserProfileUseCase by lazy {
        UpdateUserProfileUseCase(authRepository)
    }
    override val updateUserStatsUseCase: UpdateUserStatsUseCase by lazy {
        UpdateUserStatsUseCase(authRepository)
    }

    override val getProfileCompletionUseCase: GetProfileCompletionUseCase by lazy {
        GetProfileCompletionUseCase(authRepository)
    }

    // For the SignIn and SignUp screens
    override val validateFirstName: ValidateFirstName by lazy {
        ValidateFirstName()
    }
    override val validateLastName: ValidateLastName by lazy {
        ValidateLastName()
    }
    override val validateEmail: ValidateEmail by lazy {
        ValidateEmail()
    }
    override val validatePassword: ValidatePassword by lazy {
        ValidatePassword()
    }
    override val validateTerms: ValidateTerms by lazy {
        ValidateTerms()
    }

    // For the RoadMap
    override val roadMapRepository: RoadMapRepository by lazy {
        RoadMapRepositoryImpl(firestoreDataSource)
    }
    override val getRoadMapUseCase: GetRoadMapUseCase by lazy {
        GetRoadMapUseCase(roadMapRepository, authRepository)
    }
    override val getCourseUseCase: GetCourseUseCase by lazy {
        GetCourseUseCase(roadMapRepository)
    }
    override val getLessonUseCase: GetLessonUseCase by lazy {
        GetLessonUseCase(roadMapRepository)
    }
    override val getQuestionsUseCase: GetQuestionsUseCase by lazy {
        GetQuestionsUseCase(roadMapRepository)
    }
    override val getLessonsUseCase: GetLessonsUseCase by lazy {
        GetLessonsUseCase(roadMapRepository)
    }

    // For the ExploringPathScreen
    override val exploringPathRepository: ExploringPathRepository by lazy {
        ExploringPathRepositoryImpl(firestoreDataSource)
    }
    override val getCoursesBasedOnExperienceUseCase: GetCoursesBasedOnExperienceUseCase by lazy {
        GetCoursesBasedOnExperienceUseCase(exploringPathRepository)
    }

    // For the NavigationDrawer
    override val getNavigationDrawerItemsUseCase: GetNavigationDrawerItemsUseCase by lazy {
        GetNavigationDrawerItemsUseCase(NavigationDrawer())
    }
}