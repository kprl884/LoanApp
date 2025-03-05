package com.loanmanagementapp.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.loanmanagementapp.navigation.NavRegisterer
import com.loanmanagementapp.screen.Screen

class HomeNavRegisterer : NavRegisterer {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController,
    ) {
        navGraphBuilder.composable<Screen.HomeScreen> {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

            HomeScreen(
                homeUiState = homeUiState,
                onEvent = { homeUiEvent ->
                    when (homeUiEvent) {
                        HomeUIEvent.Logout -> {
                            homeViewModel.logout()
                            navController.navigate(Screen.LoginScreen)
                        }

                        else -> homeViewModel.onEvent(homeUiEvent)
                    }
                }
            )
        }
    }
}