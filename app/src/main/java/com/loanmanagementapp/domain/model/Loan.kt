package com.loanmanagementapp.domain.model

data class Loan(
    val id: String,
    val type: LoanType,
    val amount: Double,
    val term: Int,
    val interestRate: Double,
    val borrowerId: String,
    val status: LoanStatus = LoanStatus.PENDING,
    val createdAt: Long = System.currentTimeMillis()
)