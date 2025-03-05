package com.loanmanagementapp.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.loanmanagementapp.domain.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    private val preferences: SharedPreferences
) {
    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_NAME = "user_name"
    }

    fun saveUser(user: User) {
        preferences.edit {
            putString(KEY_USER_ID, user.id)
            putString(KEY_USER_EMAIL, user.email)
            putString(KEY_USER_NAME, user.name)
        }
    }

    fun getUser(): User? {
        val id = preferences.getString(KEY_USER_ID, null) ?: return null
        val email = preferences.getString(KEY_USER_EMAIL, null) ?: return null
        val name = preferences.getString(KEY_USER_NAME, null) ?: return null

        return User(id, email, name)
    }

    fun clearUser() {
        preferences.edit {
            remove(KEY_USER_ID)
            remove(KEY_USER_EMAIL)
            remove(KEY_USER_NAME)
        }
    }

    fun isUserLoggedIn(): Boolean = getUser() != null
} 