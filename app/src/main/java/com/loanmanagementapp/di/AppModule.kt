package com.loanmanagementapp.di

import com.loanmanagementapp.data.LoanRepository
import com.loanmanagementapp.data.LoanService
import com.loanmanagementapp.data.MockLoanService
import com.loanmanagementapp.domain.strategy.LoanStatusUpdateStrategy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLoanService(): LoanService = MockLoanService()

    @Provides
    @Singleton
    fun provideLoanRepository(loanService: LoanService, loanStatusUpdateStrategy: LoanStatusUpdateStrategy): LoanRepository = LoanRepository(loanService, loanStatusUpdateStrategy)
}
