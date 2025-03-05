package com.loanmanagementapp.data

import android.content.Context
import com.loanmanagementapp.domain.strategy.LoanStatusUpdateStrategy
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class LoanRepository @Inject constructor(
    private val loanService: LoanService,
    private val loanStatusUpdateStrategy: LoanStatusUpdateStrategy
) {
    suspend fun updateLoans(context: Context): Flow<List<Loan>> = flow {
        val dataLoans = loanService.loadLoans(context)

        val updatedLoans = dataLoans.map { loan ->
            loanStatusUpdateStrategy.updateLoanStatus(loan)
        }

        loanService.saveLoans(updatedLoans)

        emit(updatedLoans)
    }
}