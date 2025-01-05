package com.plenart.emotionstationcompose.data.authentication

import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    val currentUser: Flow<User?>
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
