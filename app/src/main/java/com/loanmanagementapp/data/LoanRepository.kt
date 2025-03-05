package com.loanmanagementapp.data

import android.content.Context
import com.loanmanagementapp.domain.strategy.LoanStatusUpdateStrategy
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LoanRepository @Inject constructor(
    private val loanService: LoanService,
    private val loanStatusUpdateStrategy: LoanStatusUpdateStrategy
) {
    suspend fun updateLoans(context: Context): List<Loan> {
        val loans = loanService.loadLoans(context).toMutableList()

        val updatedLoans = loans.map { loan ->
            loanStatusUpdateStrategy.updateLoanStatus(loan)
        }
        loanService.saveLoans(updatedLoans)
        return loans
    }
}