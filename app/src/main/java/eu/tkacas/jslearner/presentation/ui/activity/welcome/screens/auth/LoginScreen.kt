package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth

import android.annotation.SuppressLint
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tkacas.jslearner.R
import eu.tkacas.jslearner.presentation.ui.component.AuthButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.AuthHeadingTextComponent
import eu.tkacas.jslearner.presentation.ui.component.AuthTextFieldComponent
import eu.tkacas.jslearner.presentation.ui.component.DividerTextComponent
import eu.tkacas.jslearner.presentation.ui.component.HaveAnAccountOrNotClickableTextComponent
import eu.tkacas.jslearner.presentation.ui.component.PasswordTextFieldComponent
import eu.tkacas.jslearner.presentation.ui.events.LoginFormEvent
import eu.tkacas.jslearner.presentation.ui.state.LoginFormState
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.LoginViewModel
import eu.tkacas.jslearner.domain.Result

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
    state: LoginFormState = viewModel.state
) {
    val context = LocalContext.current

    val errorMessage = remember { mutableStateOf("") }

    LaunchedEffect(viewModel) {
        viewModel?.loginFlow?.collect {
            when (it) {
                is Result.Error -> {
                    errorMessage.value = it.exception.message.toString()
                }
                is Result.Loading -> {
                    //Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }
                is Result.Success<*> -> {
                    Toast.makeText(context, "Successful Login", Toast.LENGTH_LONG).show()
                    //navController.navigate("home")
                }

                null -> {}
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

            Spacer(modifier = Modifier.height(20.dp))


            Text(
                text = errorMessage.value,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.error
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
                            navController.navigate("signUp")
                        }
                    }
                )
            }
        }
    }
}