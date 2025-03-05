package com.loanmanagementapp.ui.screen.home

import com.loanmanagementapp.data.Loan

data class HomeUIState(
    val deneme: String = "",
    var listOfLoan: List<Loan> = emptyList()
)