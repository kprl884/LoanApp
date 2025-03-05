package com.loanmanagementapp.domain.strategy

class PersonalLoanStrategy : LoanStrategy {
    override fun calculateInterest(principal: Double, term: Int): Double {
        // Personal loans have higher interest rates
        val baseRate = getBaseInterestRate()
        // Add term-based adjustment - longer terms have higher rates
        val termAdjustment = (term / 12.0) * 0.5
        return principal * (baseRate + termAdjustment) * (term / 12.0)
    }

    override fun getMinTerm(): Int = 6  // 6 months minimum

    override fun getMaxTerm(): Int = 60 // 5 years maximum

    override fun getBaseInterestRate(): Double = 0.12 // 12% base rate
}