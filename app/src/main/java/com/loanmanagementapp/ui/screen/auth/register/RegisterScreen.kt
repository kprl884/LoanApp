package com.loanmanagementapp.ui.screen.auth.register

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.loanmanagementapp.ui.components.CustomTextField
import com.loanmanagementapp.ui.components.PrimaryButton
import com.loanmanagementapp.ui.screen.auth.AuthEvent
import com.loanmanagementapp.ui.screen.auth.AuthState

@Composable
fun RegisterScreen(
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
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            value = state.name,
            onValueChange = { onEvent(AuthEvent.NameChanged(it)) },
            label = "Full Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = state.email,
            onValueChange = { onEvent(AuthEvent.EmailChanged(it)) },
            label = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = state.password,
            onValueChange = { onEvent(AuthEvent.PasswordChanged(it)) },
            label = "Password",
            visualTransformation = PasswordVisualTransformation()
        )

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
            text = "Register",
            onClick = { onEvent(AuthEvent.RegisterClicked) },
            isLoading = state.isLoading
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { onEvent(AuthEvent.NavigateToLogin) }
        ) {
            Text("Already have an account? Login")
        }
    }
} 