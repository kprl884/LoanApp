package com.loanmanagementapp.domain.strategy

import com.loanmanagementapp.data.Loan
import com.loanmanagementapp.domain.model.LoanStatus
import javax.inject.Inject

interface LoanStatusUpdateStrategy {
    fun updateLoanStatus(loan: Loan): Loan
}

class DefaultLoanStatusUpdateStrategy @Inject constructor() : LoanStatusUpdateStrategy {
    override fun updateLoanStatus(loan: Loan): Loan {
        // Clone the loan to avoid direct mutation
        val updatedLoan = loan.copy(
            dueIn = loan.dueIn - 1 // Decrement due days for all loans
        )
        
        // Apply interest rate changes
        val interestRate = if (updatedLoan.status != LoanStatus.PAID && 
                               updatedLoan.status != LoanStatus.DEFAULT && 
                               updatedLoan.dueIn > 0) {
            updatedLoan.interestRate + 0.5
        } else {
            updatedLoan.interestRate
        }
        
        // Determine new status
        val status = when {
            // For overdue loans with high principal, set to default
            updatedLoan.status == LoanStatus.OVERDUE && updatedLoan.principalAmount > 5000 -> 
                LoanStatus.DEFAULT
            
            // For past due loans that aren't paid
            updatedLoan.dueIn < 0 && updatedLoan.status != LoanStatus.PAID -> {
                if (updatedLoan.principalAmount > 0) LoanStatus.OVERDUE else LoanStatus.DEFAULT
            }
            
            // For paid status with small principal
            (updatedLoan.status == LoanStatus.PAID || updatedLoan.status == LoanStatus.APPROVED) && 
            updatedLoan.principalAmount < 1000 -> LoanStatus.PAID
            
            // Default: keep current status
            else -> updatedLoan.status
        }
        
        return updatedLoan.copy(
            interestRate = interestRate,
            status = status
        )
    }
}