package eu.tkacas.jslearner.ui.activities.welcome.screens.auth

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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.ui.components.AuthButtonComponent
import eu.tkacas.jslearner.ui.components.AuthHeadingTextComponent
import eu.tkacas.jslearner.ui.components.AuthTextFieldComponent
import eu.tkacas.jslearner.ui.components.DividerTextComponent
import eu.tkacas.jslearner.ui.components.HaveAnAccountOrNotClickableTextComponent
import eu.tkacas.jslearner.ui.components.PasswordTextFieldComponent
import eu.tkacas.jslearner.ui.events.LoginFormEvent
import eu.tkacas.jslearner.ui.states.LoginFormState
import eu.tkacas.jslearner.ui.viewModel.auth.BaseAuthViewModel
import eu.tkacas.jslearner.ui.viewModel.auth.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    state: LoginFormState
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when(event) {
                is BaseAuthViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Login successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 28.dp, end = 28.dp, top = 60.dp, bottom = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AuthHeadingTextComponent(value = stringResource(id = R.string.welcome_to_jslearner_app))
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
            PasswordTextFieldComponent(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginFormEvent.PasswordChanged(it)) },
                labelValue = stringResource(id = R.string.password),
                supportedTextValue = state.passwordError ?: "",
                errorStatus = state.passwordError != null
            )

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
                    AuthButtonComponent(
                        value = stringResource(R.string.login),
                        onButtonClicked = {
                            viewModel.onEvent(LoginFormEvent.Submit)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                HaveAnAccountOrNotClickableTextComponent(
                    alreadyHaveAnAccount = false,
                    onTextSelected = {
                        if (it == "Register") {
                            viewModel.loginActions.navigateToSignUp()
                        }
                    }
                )
            }
        }
    }
}