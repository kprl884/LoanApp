package com.loanmanagementapp.ui.screen.auth.register

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loanmanagementapp.navigation.NavRegisterer
import com.loanmanagementapp.screen.Screen
import com.loanmanagementapp.ui.screen.auth.AuthEvent
import com.loanmanagementapp.ui.screen.auth.AuthViewModel

class RegisterNavRegister : NavRegisterer {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable<Screen.RegisterScreen> {
            val authViewModel: AuthViewModel = hiltViewModel()
            val loginUiState by authViewModel.state.collectAsStateWithLifecycle()

            RegisterScreen(
                state = loginUiState,
                onEvent = { authEvent ->
                    when (authEvent) {
                        is AuthEvent.NavigateToLogin -> navController.navigate(Screen.LoginScreen)
                        is AuthEvent.NavigateToHome -> navController.navigate(Screen.HomeScreen)
                        else -> authViewModel.onEvent(authEvent)
                    }
                }
            )
        }
    }
}