package com.loanmanagementapp.domain.factory

import com.loanmanagementapp.domain.model.LoanType
import javax.inject.Inject
import javax.inject.Singleton
import com.loanmanagementapp.domain.strategy.AutoLoanStrategy
import com.loanmanagementapp.domain.strategy.LoanStrategy
import com.loanmanagementapp.domain.strategy.MortgageLoanStrategy
import com.loanmanagementapp.domain.strategy.PersonalLoanStrategy

@Singleton
class LoanStrategyFactory @Inject constructor() {
    fun createStrategy(type: LoanType): LoanStrategy {
        return when (type) {
            LoanType.PERSONAL -> PersonalLoanStrategy()
            LoanType.AUTO -> AutoLoanStrategy()
            LoanType.MORTGAGE -> MortgageLoanStrategy()
        }
    }
}