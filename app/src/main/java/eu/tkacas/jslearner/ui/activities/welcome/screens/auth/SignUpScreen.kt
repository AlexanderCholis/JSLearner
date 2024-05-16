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
import eu.tkacas.jslearner.ui.components.TermsCheckboxComponent
import eu.tkacas.jslearner.ui.events.SignUpFormEvent
import eu.tkacas.jslearner.ui.states.SignUpFormState
import eu.tkacas.jslearner.ui.viewModel.auth.BaseAuthViewModel
import eu.tkacas.jslearner.ui.viewModel.auth.SignUpViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel,
    state: SignUpFormState
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when(event) {
                is BaseAuthViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Registration successful",
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
            AuthHeadingTextComponent(value = stringResource(id = R.string.create_an_account))
            AuthTextFieldComponent(
                value = state.firstName,
                onValueChange = { viewModel.onEvent(SignUpFormEvent.FirstNameChanged(it)) },
                labelValue = stringResource(id = R.string.first_name),
                painterResource = painterResource(id = R.drawable.person),
                contentDescription = stringResource(id = R.string.first_name_hint),
                keyboardType = KeyboardType.Text,
                supportedTextValue = state.firstNameError ?: "",
                errorStatus = state.firstNameError != null
            )
            AuthTextFieldComponent(
                value = state.lastName,
                onValueChange = { viewModel.onEvent(SignUpFormEvent.LastNameChanged(it)) },
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.person),
                contentDescription = stringResource(id = R.string.last_name_hint),
                keyboardType = KeyboardType.Text,
                supportedTextValue = state.lastNameError ?: "",
                errorStatus = state.lastNameError != null
            )
            AuthTextFieldComponent(
                value = state.email,
                onValueChange = { viewModel.onEvent(SignUpFormEvent.EmailChanged(it)) },
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.email),
                contentDescription = stringResource(id = R.string.email_hint),
                keyboardType = KeyboardType.Email,
                supportedTextValue = state.emailError ?: "",
                errorStatus = state.emailError != null
            )
            PasswordTextFieldComponent(
                value = state.password,
                onValueChange = { viewModel.onEvent(SignUpFormEvent.PasswordChanged(it)) },
                labelValue = stringResource(id = R.string.password),
                supportedTextValue = state.passwordError ?: "",
                errorStatus = state.passwordError != null
            )
            TermsCheckboxComponent(
                checkedValue = state.acceptedTerms,
                onCheckedChange = { viewModel.onEvent(SignUpFormEvent.AcceptTerms(it)) },
                onTextSelected = {
                    if (it == "Privacy Policy") {
                        viewModel.signUpActions.navigateToPrivacy()
                    } else if (it == "Terms of Use.") {
                        viewModel.signUpActions.navigateToTerms()
                    }
                },
                errorMessageValue = state.termsError ?: "",
                errorStatus = state.termsError != null
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
                ){
                    AuthButtonComponent(
                        value = stringResource(R.string.register),
                        onButtonClicked = {
                            viewModel.onEvent(SignUpFormEvent.Submit)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                HaveAnAccountOrNotClickableTextComponent(
                    alreadyHaveAnAccount = true,
                    onTextSelected = {
                        if (it == "Login") {
                            viewModel.signUpActions.navigateToLogin()
                        }
                    }
                )
            }
        }
    }
}