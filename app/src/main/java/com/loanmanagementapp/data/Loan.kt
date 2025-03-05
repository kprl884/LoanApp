package com.loanmanagementapp.data

import com.loanmanagementapp.domain.model.LoanStatus
import com.loanmanagementapp.domain.model.LoanType

data class Loan(
    val name: String,
    var principalAmount: Double,
    var interestRate: Double,
    var status: LoanStatus,
    var dueIn: Int,
    var loanType: String = LoanType.PERSONAL.name
)