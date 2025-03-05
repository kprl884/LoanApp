package com.loanmanagementapp.di

import android.content.Context
import android.content.SharedPreferences
import com.loanmanagementapp.data.local.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            "loan_app_preferences",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providePreferencesManager(preferences: SharedPreferences): PreferencesManager {
        return PreferencesManager(preferences)
    }
}

