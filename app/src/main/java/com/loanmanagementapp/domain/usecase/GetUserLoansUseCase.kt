package com.loanmanagementapp.domain.usecase


import android.content.Context
import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.data.LoanRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLoansUseCase @Inject constructor(
    private val loanRepository: LoanRepository
) {
    suspend operator fun invoke(context: Context): Flow<List<Loan>> {
        return loanRepository.updateLoans(context)
    }
}