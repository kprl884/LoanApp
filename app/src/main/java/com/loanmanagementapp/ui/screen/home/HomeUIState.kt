package com.loanmanagementapp.ui.screen.home

import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.domain.model.LoanType

data class HomeUIState(
    val selectedLoanType: LoanType = LoanType.PERSONAL,
    val amount: String = "",
    val term: String = "",
    val calculatedInterest: Double? = null,
    val monthlyPayment: Double? = null,
    val listOfLoan: List<Loan> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)