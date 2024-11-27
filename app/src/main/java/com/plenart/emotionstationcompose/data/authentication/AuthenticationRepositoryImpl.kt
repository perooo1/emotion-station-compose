package com.plenart.emotionstationcompose.data.authentication

import com.google.firebase.auth.FirebaseAuth

class AuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : IAuthenticationRepository {
    override suspend fun signUp(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }


}
