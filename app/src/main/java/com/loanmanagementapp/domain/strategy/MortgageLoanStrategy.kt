package com.loanmanagementapp.domain.strategy

class MortgageLoanStrategy : LoanStrategy {
    //todo figures can be customized according to interest rates and months
    override fun calculateInterest(principal: Double, term: Int): Double {
        // Mortgage loans have lower interest rates but longer terms
        val baseRate = getBaseInterestRate()
        // Add principal-based tiered rates
        val principalAdjustment = when {
            principal > 500000 -> -0.005
            principal > 200000 -> -0.0025
            else -> 0.0
        }
        return principal * (baseRate + principalAdjustment) * (term / 12.0)
    }

    override fun getMinTerm(): Int = 60   // 5 years minimum

    override fun getMaxTerm(): Int = 360  // 30 years maximum

    override fun getBaseInterestRate(): Double = 0.045 // 4.5% base rate
}