package com.plenart.emotionstationcompose.data.authentication

interface AuthenticationRepository {
    suspend fun signUp(email: String, password: String)
    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
}
