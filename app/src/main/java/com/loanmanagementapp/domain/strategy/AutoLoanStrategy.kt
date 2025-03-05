package com.loanmanagementapp.domain.strategy

class AutoLoanStrategy : LoanStrategy {
    //todo figures can be customized according to interest rates and months
    override fun calculateInterest(principal: Double, term: Int): Double {
        val baseRate = getBaseInterestRate()
        val principalAdjustment = if (principal > 20000) -0.01 else 0.0
        return principal * (baseRate + principalAdjustment) * (term / 12.0)
    }

    override fun getMinTerm(): Int = 12  // 1 year minimum

    override fun getMaxTerm(): Int = 72  // 6 years maximum

    override fun getBaseInterestRate(): Double = 0.08 // 8% base rate
}