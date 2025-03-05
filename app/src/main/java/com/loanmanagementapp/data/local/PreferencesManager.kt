package com.loanmanagementapp.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.loanmanagementapp.domain.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    private val preferences: SharedPreferences
) {
    companion object {
        // Session keys
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_CURRENT_USER_ID = "current_user_id"
        
        // User data keys
        private const val KEY_USER_ID_PREFIX = "user_id_"
        private const val KEY_USER_EMAIL_PREFIX = "user_email_"
        private const val KEY_USER_NAME_PREFIX = "user_name_"
    }

    fun saveUser(user: User) {
        // Save user data with unique keys for each user
        preferences.edit {
            putString("${KEY_USER_ID_PREFIX}${user.email}", user.id)
            putString("${KEY_USER_EMAIL_PREFIX}${user.email}", user.email)
            putString("${KEY_USER_NAME_PREFIX}${user.email}", user.name)
            
            // Set current session
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_CURRENT_USER_ID, user.email)
        }
    }

    fun getUser(): User? {
        val currentUserEmail = preferences.getString(KEY_CURRENT_USER_ID, null) ?: return null
        if (!preferences.getBoolean(KEY_IS_LOGGED_IN, false)) return null

        return getUserByEmail(currentUserEmail)
    }

    fun getUserByEmail(email: String): User? {
        val id = preferences.getString("${KEY_USER_ID_PREFIX}${email}", null) ?: return null
        val savedEmail = preferences.getString("${KEY_USER_EMAIL_PREFIX}${email}", null) ?: return null
        val name = preferences.getString("${KEY_USER_NAME_PREFIX}${email}", null) ?: return null

        return User(id, savedEmail, name)
    }

    fun logout() {
        // Only clear session data, keep user registration data
        preferences.edit {
            remove(KEY_IS_LOGGED_IN)
            remove(KEY_CURRENT_USER_ID)
        }
    }

    fun isUserRegistered(email: String): Boolean {
        return preferences.contains("${KEY_USER_EMAIL_PREFIX}${email}")
    }

    fun isLoggedIn(): Boolean {
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
