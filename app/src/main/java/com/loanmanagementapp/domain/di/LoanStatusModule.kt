package com.loanmanagementapp.domain.di

import com.loanmanagementapp.domain.strategy.DefaultLoanStatusUpdateStrategy
import com.loanmanagementapp.domain.strategy.LoanStatusUpdateStrategy
import dagger.Module
import dagger.hilt.InstallIn
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StrategyModule {

    @Provides
    @Singleton
    fun provideLoanStatusUpdateStrategy(): LoanStatusUpdateStrategy {
        return DefaultLoanStatusUpdateStrategy()
    }
}