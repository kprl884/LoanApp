package com.loanmanagementapp.ui.screen.home

import android.content.Context

sealed interface HomeUIEvent {
    data class UpdateLoans(val context: Context) : HomeUIEvent
    data object Logout : HomeUIEvent
}