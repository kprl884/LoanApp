package com.loanmanagementapp.ui.screen.auth

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loanmanagementapp.navigation.NavRegisterer
import com.loanmanagementapp.screen.Screen

class AuthNavRegisterer : NavRegisterer {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        navGraphBuilder.composable<Screen.LoginScreen> {
            val authViewModel: AuthViewModel = hiltViewModel()
            val loginUiState by authViewModel.state.collectAsStateWithLifecycle()

            LoginScreen(
                state = loginUiState,
                onEvent = { authEvent ->
                    when (authEvent) {
                        is AuthEvent.NavigateToHome -> navController.navigate(Screen.HomeScreen)
                        is AuthEvent.NavigateToRegister -> navController.navigate(Screen.RegisterScreen)
                        else -> authViewModel.onEvent(authEvent)
                    }
                }
            )
        }
    }
}