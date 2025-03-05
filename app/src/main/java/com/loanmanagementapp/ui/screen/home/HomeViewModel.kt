package com.loanmanagementapp.ui.screen.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.data.local.PreferencesManager
import com.loanmanagementapp.domain.factory.LoanStrategyFactory
import com.loanmanagementapp.domain.usecase.GetUserLoansUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserLoansUseCase: GetUserLoansUseCase,
    private val loanStrategyFactory: LoanStrategyFactory,
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
            is HomeUIEvent.AmountChanged -> {
                _homeUiState.update { it.copy(amount = homeUIEvent.amount) }
            }
            is HomeUIEvent.TermChanged -> {
                _homeUiState.update { it.copy(term = homeUIEvent.term) }
            }
            is HomeUIEvent.LoanTypeSelected -> {
                _homeUiState.update { it.copy(selectedLoanType = homeUIEvent.type) }
            }
            HomeUIEvent.CalculateLoan -> calculateLoan()
            HomeUIEvent.Logout -> Unit
            is HomeUIEvent.UpdateLoans -> updateLoan(homeUIEvent.context)
        }
    }

    private fun updateLoan(context: Context) {
        viewModelScope.launch {
            _homeUiState.update { it.copy(isLoading = true) }

            getUserLoansUseCase(context)
                .onStart {
                    // Optional: You can update UI state when flow starts
                }
                .catch { exception ->
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Error loading loans: ${exception.message}"
                        )
                    }
                }
                .collect { loans ->
                    _homeUiState.update {
                        it.copy(
                            listOfLoan = loans,
                            isLoading = false,
                            errorMessage = null
                        )
                    }
                }
        }
    }

    private fun calculateLoan() {
        viewModelScope.launch {
            try {
                _homeUiState.update { it.copy(isLoading = true) }

                val amount = _homeUiState.value.amount.toDoubleOrNull()
                val term = _homeUiState.value.term.toIntOrNull()

                if (amount == null || term == null) {
                    _homeUiState.update {
                        it.copy(
                            errorMessage = "Please enter valid amount and term",
                            isLoading = false
                        )
                    }
                    return@launch
                }

                val strategy = loanStrategyFactory.createStrategy(_homeUiState.value.selectedLoanType)

                if (term < strategy.getMinTerm() || term > strategy.getMaxTerm()) {
                    _homeUiState.update {
                        it.copy(
                            errorMessage = "Term must be between ${strategy.getMinTerm()} and ${strategy.getMaxTerm()} months",
                            isLoading = false
                        )
                    }
                    return@launch
                }

                val interest = strategy.calculateInterest(amount, term)
                val monthlyPayment = strategy.calculateMonthlyPayment(amount, term)

                _homeUiState.update {
                    it.copy(
                        calculatedInterest = interest,
                        monthlyPayment = monthlyPayment,
                        isLoading = false,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _homeUiState.update {
                    it.copy(
                        errorMessage = e.message ?: "Error calculating loan",
                        isLoading = false
                    )
                }
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