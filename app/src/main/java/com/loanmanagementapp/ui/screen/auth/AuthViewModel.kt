package com.loanmanagementapp.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loanmanagementapp.data.local.PreferencesManager
import com.loanmanagementapp.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state.asStateFlow()

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val user = preferencesManager.getUser()
        _state.value = _state.value.copy(
            isLoggedIn = user != null,
            user = user
        )
    }

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email,
                    errorMessage = null
                )
            }

            is AuthEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password,
                    errorMessage = null
                )
            }

            is AuthEvent.NameChanged -> {
                _state.value = _state.value.copy(
                    name = event.name,
                    errorMessage = null
                )
            }

            AuthEvent.LoginClicked -> performLogin()
            AuthEvent.RegisterClicked -> performRegistration()
            AuthEvent.LogoutClicked -> performLogout()
            AuthEvent.NavigateToHome -> Unit
            AuthEvent.NavigateToLogin -> Unit
            AuthEvent.NavigateToRegister -> Unit
        }
    }

    private fun performRegistration() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            if (!isValidInput()) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = "Please fill all fields correctly"
                )
                return@launch
            }

            val user = User(
                id = System.currentTimeMillis().toString(),
                email = state.value.email,
                name = state.value.name
            )

            preferencesManager.saveUser(user)
            _state.value = _state.value.copy(
                isLoading = false,
                isLoggedIn = true,
                user = user
            )
        }
    }

    private fun performLogin() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            val savedUser = preferencesManager.getUser()
            if (savedUser?.email == state.value.email) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    isLoggedIn = true,
                    user = savedUser
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = "Invalid credentials"
                )
            }
        }
    }

    private fun performLogout() {
        viewModelScope.launch {
            preferencesManager.clearUser()
            _state.value = AuthState()
        }
    }

    private fun isValidInput(): Boolean {
        return state.value.run {
            email.isNotBlank() &&
                    password.isNotBlank() &&
                    (isRegistration && name.isNotBlank() || !isRegistration)
        }
    }
}

