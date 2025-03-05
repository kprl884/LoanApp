package com.loanmanagementapp.domain.strategy

import org.junit.Assert.assertEquals
import org.junit.Test

class LoanStrategyTest {
    
    @Test
    fun `PersonalLoanStrategy calculates correct interest for different terms`() {
        val strategy = PersonalLoanStrategy()
        val principal = 10000.0
        
        // 1 year term
        val oneYearInterest = strategy.calculateInterest(principal, 12)
        assertEquals(1200.0, oneYearInterest, 0.01)
        
        // 3 year term (with adjustment)
        val threeYearInterest = strategy.calculateInterest(principal, 36)
        assertEquals(3900.0, threeYearInterest, 0.01)
        
        // 5 year term (with higher adjustment)
        val fiveYearInterest = strategy.calculateInterest(principal, 60)
        assertEquals(7000.0, fiveYearInterest, 0.01)
    }
    
    @Test
    fun `AutoLoanStrategy calculates correct interest for different terms`() {
        val strategy = AutoLoanStrategy()
        val principal = 20000.0
        
        // 3 year term
        val threeYearInterest = strategy.calculateInterest(principal, 36)
        assertEquals(3600.0, threeYearInterest, 0.01)
        
        // 5 year term (with adjustment)
        val fiveYearInterest = strategy.calculateInterest(principal, 60)
        assertEquals(6500.0, fiveYearInterest, 0.01)
        
        // 7 year term (with higher adjustment)
        val sevenYearInterest = strategy.calculateInterest(principal, 84)
        assertEquals(9800.0, sevenYearInterest, 0.01)
    }
    
    @Test
    fun `MortgageLoanStrategy calculates correct interest for different terms`() {
        val strategy = MortgageLoanStrategy()
        val principal = 300000.0
        
        // 15 year term
        val fifteenYearInterest = strategy.calculateInterest(principal, 180)
        assertEquals(202500.0, fifteenYearInterest, 0.01)
        
        // 30 year term (with adjustment)
        val thirtyYearInterest = strategy.calculateInterest(principal, 360)
        assertEquals(421200.0, thirtyYearInterest, 0.01)
    }
} 