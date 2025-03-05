package com.loanmanagementapp.ui.screen.auth

sealed class AuthEvent {
    data class EmailChanged(val email: String) : AuthEvent()
    data class PasswordChanged(val password: String) : AuthEvent()
    data class NameChanged(val name: String) : AuthEvent()
    data object LoginClicked : AuthEvent()
    data object RegisterClicked : AuthEvent()
    data object LogoutClicked : AuthEvent()
    data object NavigateToLogin : AuthEvent()
    data object NavigateToRegister : AuthEvent()
    data object NavigateToHome : AuthEvent()
}