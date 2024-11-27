package com.plenart.emotionstationcompose.data.authentication

interface IAuthenticationRepository {
    suspend fun signUp(email: String, password: String)
    suspend fun signIn(email: String, password: String)
    suspend fun signOut()
}
