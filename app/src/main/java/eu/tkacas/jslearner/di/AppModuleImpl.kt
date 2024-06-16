package eu.tkacas.jslearner.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import eu.tkacas.jslearner.data.repository.AuthRepositoryImpl
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateTerms

class AppModuleImpl(
    private val appContext: Context
) : AppModule {
    override fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    override fun getFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    override val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(getFirebaseAuth(), getFirebaseDatabase())
    }

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
}