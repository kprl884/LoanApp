package com.loanmanagementapp.ui.screen.home

import android.content.Context
import com.loanmanagementapp.domain.model.LoanType

sealed interface HomeUIEvent {
    data class UpdateLoans(val context: Context) : HomeUIEvent
    data object Logout : HomeUIEvent
    data class AmountChanged(val amount: String) : HomeUIEvent
    data class TermChanged(val term: String) : HomeUIEvent
    data class LoanTypeSelected(val type: LoanType) : HomeUIEvent
    data object CalculateLoan : HomeUIEvent
}