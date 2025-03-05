package com.loanmanagementapp.ui.screen.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.ui.components.CustomTextField
import com.loanmanagementapp.ui.components.PrimaryButton

@Composable
fun LoginScreen(
    state: AuthState,
    onEvent: (AuthEvent) -> Unit
) {
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn) {
            onEvent(AuthEvent.NavigateToHome)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (state.isRegistration) "Register" else "Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            value = state.email,
            onValueChange = {
                onEvent(AuthEvent.EmailChanged(it))
            },
            label = "Email",
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = state.password,
            onValueChange = {
                onEvent(AuthEvent.PasswordChanged(it))
            },
            label = "Password",
            visualTransformation = PasswordVisualTransformation()
        )

        if (state.isRegistration) {
            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = state.name,
                onValueChange = {
                    onEvent(AuthEvent.NameChanged(it))
                },
                label = "Name"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        state.errorMessage?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        PrimaryButton(
            text = if (state.isRegistration) "Register" else "Login",
            onClick = {
                if (state.isRegistration) {
                    onEvent(AuthEvent.RegisterClicked)
                } else {
                    onEvent(AuthEvent.LoginClicked)
                }
            },
            isLoading = state.isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                onEvent(AuthEvent.NavigateToRegister)
            }
        ) {
            Text(
                text = if (state.isRegistration) {
                    "Already have an account? Login"
                } else {
                    "Don't have an account? Register"
                }
            )
        }
    }
}