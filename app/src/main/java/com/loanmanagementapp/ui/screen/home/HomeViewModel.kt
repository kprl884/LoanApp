package com.loanmanagementapp.ui.screen.home

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.data.local.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loanRepository: LoanRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUIState())
    val homeUiState: StateFlow<HomeUIState> = _homeUiState
        .onStart {}
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5_000),
            initialValue = HomeUIState()
        )

    fun onEvent(homeUIEvent: HomeUIEvent) {
        when (homeUIEvent) {
            HomeUIEvent.Logout -> Unit
            is HomeUIEvent.UpdateLoans -> updateLoan(homeUIEvent.context)
        }
    }

    private fun updateLoan(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            _homeUiState.update {
                it.copy(
                    listOfLoan = loanRepository.updateLoans(context)
                )
            }
        }
    }

    // todo This function can be used when we want to delete the user from the local
    fun clearUser() {
        viewModelScope.launch {
            preferencesManager.clearUser()
        }
    }
}