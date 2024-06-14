package eu.tkacas.jslearner.di

import com.google.firebase.auth.FirebaseAuth
import eu.tkacas.jslearner.domain.repository.AuthRepository
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateEmail
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateFirstName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateLastName
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidatePassword
import eu.tkacas.jslearner.domain.usecase.validateregex.ValidateTerms

interface AppModule {
    fun getFirebaseAuth(): FirebaseAuth

    val authRepository: AuthRepository

    val validateFirstName: ValidateFirstName
    val validateLastName: ValidateLastName
    val validateEmail: ValidateEmail
    val validatePassword: ValidatePassword
    val validateTerms: ValidateTerms

}