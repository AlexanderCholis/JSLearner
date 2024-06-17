package eu.tkacas.jslearner.presentation.ui.activity.welcome.screens.auth

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import eu.tkacas.jslearner.presentation.ui.component.GeneralButtonComponent
import eu.tkacas.jslearner.presentation.ui.component.AuthHeadingTextComponent
import eu.tkacas.jslearner.presentation.ui.component.AuthTextFieldComponent
import eu.tkacas.jslearner.presentation.ui.component.DividerTextComponent
import eu.tkacas.jslearner.presentation.ui.component.HaveAnAccountOrNotClickableTextComponent
import eu.tkacas.jslearner.presentation.ui.component.PasswordTextFieldComponent
import eu.tkacas.jslearner.presentation.ui.component.TermsCheckboxComponent
import eu.tkacas.jslearner.presentation.ui.events.SignUpFormEvent
import eu.tkacas.jslearner.presentation.ui.state.auth.SignUpFormState
import eu.tkacas.jslearner.presentation.viewmodel.welcome.auth.SignUpViewModel
import eu.tkacas.jslearner.domain.Result

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel,
    state: SignUpFormState = viewModel.state
) {
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.signupFlow.collect {
            when (it) {
                is Result.Error -> {
                    // Toast.makeText(context, it.errorMessage, Toast.LENGTH_LONG).show()
                }

                is Result.Loading -> {
                    //Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }

                is Result.Success<*> -> {
                    Toast.makeText(context, "Registration successful", Toast.LENGTH_LONG).show()
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                AuthHeadingTextComponent(value = stringResource(id = R.string.create_an_account))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
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
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
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
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
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
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    PasswordTextFieldComponent(
                        value = state.password,
                        onValueChange = { viewModel.onEvent(SignUpFormEvent.PasswordChanged(it)) },
                        labelValue = stringResource(id = R.string.password),
                        supportedTextValue = state.passwordError ?: "",
                        errorStatus = state.passwordError != null
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TermsCheckboxComponent(
                        checkedValue = state.acceptedTerms,
                        onCheckedChange = { viewModel.onEvent(SignUpFormEvent.AcceptTerms(it)) },
                        onTextSelected = {
                            if (it == "Privacy Policy") {
                                navController.navigate("privacyPolicy")
                            } else if (it == "Terms of Use.") {
                                navController.navigate("termsOfUse")
                            }
                        },
                        errorMessageValue = state.termsError ?: "",
                        errorStatus = state.termsError != null
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = state.errorMessage ?: "",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.error
                )
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
                    value = stringResource(R.string.register),
                    onButtonClicked = {
                        viewModel.onEvent(SignUpFormEvent.Submit)
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            DividerTextComponent()
            HaveAnAccountOrNotClickableTextComponent(
                alreadyHaveAnAccount = true,
                onTextSelected = {
                    if (it == "Login") {
                        navController.navigate("login")
                    }
                }
            )
        }
    }
}
