package com.loanmanagementapp.ui.screen.auth

import com.loanmanagementapp.domain.User

data class AuthState(
    val email: String = "",
    val password: String = "",
    val name: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isLoggedIn: Boolean = false,
    val user: User? = null,
    val isRegistration: Boolean = false
)