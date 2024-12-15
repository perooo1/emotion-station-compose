package com.plenart.emotionstationcompose.data.authentication

import com.google.firebase.auth.FirebaseAuth
import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.model.User
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class AuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationRepository {
    override val currentUser: Flow<User?>
        get() = callbackFlow {

            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(
                    auth.currentUser?.run {
                        User(
                            id = uid,
                            email = email,
                            name = displayName,
                        )
                    },
                )
            }
            firebaseAuth.addAuthStateListener(listener)
            awaitClose {
                firebaseAuth.removeAuthStateListener(listener)
            }
        }

    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override fun hasUser(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun signUp(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    override suspend fun signIn(email: String, password: String): AuthenticationResult {
        return try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await().user

            AuthenticationResult(
                data = user?.run {
                    User(
                        id = uid,
                        email = email,
                        name = displayName,
                    )
                },
                errorMessage = null,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            AuthenticationResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}
