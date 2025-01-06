package com.plenart.emotionstationcompose.data.authentication

import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.model.User
import kotlinx.coroutines.flow.StateFlow

interface AuthenticationRepository {
    val currentUser: StateFlow<User?>
    val currentUserId: String
    suspend fun hasUser(): Boolean

    suspend fun signIn(email: String, password: String): AuthenticationResult
    suspend fun signUp(
        email: String,
        password: String,
        name: String,
        lastName: String,
        signUpAsSpecialist: Boolean,
    ): AuthenticationResult

    suspend fun signOut()
}
