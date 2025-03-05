package com.loanmanagementapp.screen

import kotlinx.serialization.Serializable

sealed class Screen : BaseScreen() {
    @Serializable
    data object LoginScreen : Screen()

    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data object RegisterScreen : Screen()
}