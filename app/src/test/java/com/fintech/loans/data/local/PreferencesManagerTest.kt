package com.fintech.loans.data.local

import android.content.SharedPreferences
import com.loanmanagementapp.domain.model.User
import com.loanmanagementapp.data.local.PreferencesManager
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class PreferencesManagerTest {
    
    private lateinit var preferencesManager: PreferencesManager
    private lateinit var mockPreferences: SharedPreferences
    private lateinit var mockEditor: SharedPreferences.Editor

    @Before
    fun setup() {
        mockPreferences = mockk(relaxed = true)
        mockEditor = mockk(relaxed = true)
        
        every { mockPreferences.edit() } returns mockEditor
        every { mockEditor.apply() } returns Unit
        
        preferencesManager = PreferencesManager(mockPreferences)
    }

    @Test
    fun `saveUser stores user data in preferences`() {
        // Given
        val user = User("123", "test@example.com", "Test User")

        // When
        preferencesManager.saveUser(user)

        // Then
        verify {
            mockEditor.putString("user_id", "123")
            mockEditor.putString("user_email", "test@example.com")
            mockEditor.putString("user_name", "Test User")
            mockEditor.apply()
        }
    }

    @Test
    fun `getUser returns null when no user data exists`() {
        // Given
        every { mockPreferences.getString(any(), any()) } returns null

        // When
        val result = preferencesManager.getUser()

        // Then
        assertNull(result)
    }

    @Test
    fun `getUser returns User when all data exists`() {
        // Given
        every { mockPreferences.getString("user_id", null) } returns "123"
        every { mockPreferences.getString("user_email", null) } returns "test@example.com"
        every { mockPreferences.getString("user_name", null) } returns "Test User"

        // When
        val result = preferencesManager.getUser()

        // Then
        assertEquals(User("123", "test@example.com", "Test User"), result)
    }

    @Test
    fun `clearUser removes all user data`() {
        // When
        preferencesManager.clearUser()

        // Then
        verify {
            mockEditor.remove("user_id")
            mockEditor.remove("user_email")
            mockEditor.remove("user_name")
            mockEditor.apply()
        }
    }

    @Test
    fun `isUserLoggedIn returns true when user exists`() {
        // Given
        every { mockPreferences.getString("user_id", null) } returns "123"
        every { mockPreferences.getString("user_email", null) } returns "test@example.com"
        every { mockPreferences.getString("user_name", null) } returns "Test User"

        // When
        val result = preferencesManager.isUserLoggedIn()

        // Then
        assertEquals(true, result)
    }

    @Test
    fun `isUserLoggedIn returns false when user doesn't exist`() {
        // Given
        every { mockPreferences.getString(any(), any()) } returns null

        // When
        val result = preferencesManager.isUserLoggedIn()

        // Then
        assertEquals(false, result)
    }
} 