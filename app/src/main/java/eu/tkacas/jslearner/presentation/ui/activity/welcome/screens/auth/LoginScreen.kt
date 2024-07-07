package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.AuthHeadingTextComponent
import eu.tkacas.jslearner.presentation.ui.component.AuthTextFieldComponent
import eu.tkacas.jslearner.presentation.ui.component.DividerTextComponent
import eu.tkacas.jslearner.presentation.ui.component.HaveAnAccountOrNotClickableTextComponent
import eu.tkacas.jslearner.presentation.ui.component.PasswordTextFieldComponent
import eu.tkacas.jslearner.presentation.ui.events.auth.LoginFormEvent
import eu.tkacas.jslearner.presentation.ui.state.auth.LoginFormState
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.LoginViewModel
import eu.tkacas.jslearner.domain.Result
import eu.tkacas.jslearner.presentation.ui.activity.main.MainActivity
import eu.tkacas.jslearner.presentation.ui.component.ErrorMessageText
import eu.tkacas.jslearner.presentation.ui.component.ProgressIndicatorComponent

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
    state: LoginFormState = viewModel.state
) {
    val context = LocalContext.current
    val loginFlowState by viewModel.loginFlow.collectAsState()
    var loadingState by remember { mutableStateOf(false) }

    LaunchedEffect(loginFlowState) {
        when (loginFlowState) {
            is Result.Error -> {
                loadingState = false
            }
            is Result.Loading -> {
                loadingState = true
            }
            is Result.Success<*> -> {
                loadingState = false
                Toast.makeText(context, "Successful Login", Toast.LENGTH_SHORT).show()
                val destination = viewModel.determineDestination()
                if (destination == "experienceLevel") {
                    navController.navigate("experienceLevel")
                }
                else {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    context.startActivity(intent)
                }
            }
            null -> {}
        }
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 28.dp, end = 28.dp, top = 60.dp, bottom = 28.dp)
    ) {
        if (loadingState) {
            ProgressIndicatorComponent()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                AuthHeadingTextComponent(value = stringResource(id = R.string.welcome_to_jslearner_app))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AuthTextFieldComponent(
                        value = state.email,
                        onValueChange = { viewModel.onEvent(LoginFormEvent.EmailChanged(it)) },
                        labelValue = stringResource(id = R.string.email),
                        painterResource = painterResource(id = R.drawable.email),
                        contentDescription = stringResource(id = R.string.email_hint),
                        keyboardType = KeyboardType.Email,
                        supportedTextValue = state.emailError ?: "",
                        errorStatus = state.emailError != null
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    PasswordTextFieldComponent(
                        value = state.password,
                        onValueChange = { viewModel.onEvent(LoginFormEvent.PasswordChanged(it)) },
                        labelValue = stringResource(id = R.string.password),
                        supportedTextValue = state.passwordError ?: "",
                        errorStatus = state.passwordError != null
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                ErrorMessageText(errorMessage = state.errorMessage)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                GeneralButtonComponent(
                    valueId = R.string.login,
                    onButtonClicked = {
                        viewModel.onEvent(LoginFormEvent.Submit)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            DividerTextComponent()
            HaveAnAccountOrNotClickableTextComponent(
                alreadyHaveAnAccount = false,
                onTextSelected = {
                    if (it == "Register") {
                        navController.navigate("signUp")
                    }
                }
            )
        }
    }
}