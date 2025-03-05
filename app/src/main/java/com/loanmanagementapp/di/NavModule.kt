package com.loanmanagementapp.di

import com.loanmanagementapp.navigation.NavProvider
import com.loanmanagementapp.navigation.NavProviderItem
import com.loanmanagementapp.ui.screen.auth.AuthNavRegisterer
import com.loanmanagementapp.ui.screen.auth.register.RegisterNavRegister
import com.loanmanagementapp.ui.screen.home.HomeNavRegisterer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NavModule {

    @Provides
    @Singleton
    fun provideNavProvider() = NavProvider(
        listOf(
            NavProviderItem(screen = AuthNavRegisterer()),
            NavProviderItem(screen = HomeNavRegisterer()),
            NavProviderItem(screen = RegisterNavRegister()),
        )
    )
}