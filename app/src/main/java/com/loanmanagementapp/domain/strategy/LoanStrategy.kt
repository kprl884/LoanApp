package com.loanmanagementapp.domain.strategy

interface LoanStrategy {
    fun calculateInterest(principal: Double, term: Int): Double
    fun getMinTerm(): Int
    fun getMaxTerm(): Int
    fun getBaseInterestRate(): Double
    fun calculateMonthlyPayment(principal: Double, term: Int): Double {
        val interest = calculateInterest(principal, term)
        val totalAmount = principal + interest
        return totalAmount / term
    }
}