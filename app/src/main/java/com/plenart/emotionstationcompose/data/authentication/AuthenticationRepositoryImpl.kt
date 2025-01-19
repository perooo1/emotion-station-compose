package com.plenart.emotionstationcompose.data.authentication

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.model.AuthenticationResult
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthenticationRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val databaseRepository: RemoteDatabaseRepository,
    coroutineScope: CoroutineScope,
) : AuthenticationRepository {

    init {
        firebaseAuth.addAuthStateListener { auth ->
            coroutineScope.launch {
                val mappedUser = mapUser(auth.currentUser)
                _currentUser.value = mappedUser
            }
        }
    }

    private val _currentUser = MutableStateFlow<User?>(null)
    override val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    override val currentUserId: String = firebaseAuth.currentUser?.uid.orEmpty()

    private suspend fun mapUser(authenticatedUser: FirebaseUser?): User? {
        var user: User? = null
        try {
            user = databaseRepository.getSpecialistFromDatabase(authenticatedUser?.uid ?: "")
            if (user == null) {
                user = databaseRepository.getParentFromDatabase(authenticatedUser?.uid ?: "")
            }
        } catch (e: Exception) {
            Log.e("AuthenticationRepositoryImpl", "Error mapping user: ${e.message}")
        }
        return user
    }

    override suspend fun hasUser(): Boolean {
        val currentUser = firebaseAuth.currentUser

        if (currentUser != null && _currentUser.value == null) {
            _currentUser.value = mapUser(currentUser) ?: User.EmptyUser
        }
        return firebaseAuth.currentUser != null
    }

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        lastName: String,
        signUpAsSpecialist: Boolean,
    ): AuthenticationResult {
        return try {
            val authUser =
                firebaseAuth.createUserWithEmailAndPassword(email, password).await().user

            val createdUser: User

            if (authUser != null) {
                if (signUpAsSpecialist) {
                    createdUser = Specialist(
                        id = authUser.uid,
                        isSpecialist = true,
                        name = name,
                        lastName = lastName,
                        email = email,
                        password = password
                    )
                    databaseRepository.registerUserInDatabase(createdUser)
                    _currentUser.value = createdUser

                    AuthenticationResult(
                        data = createdUser,
                        errorMessage = null,
                    )
                } else {
                    createdUser = Parent(
                        id = authUser.uid,
                        isSpecialist = false,
                        name = name,
                        lastName = lastName,
                        email = email,
                        password = password,
                    )
                    databaseRepository.registerUserInDatabase(createdUser)
                    _currentUser.value = createdUser

                    AuthenticationResult(
                        data = createdUser,
                        errorMessage = null,
                    )
                }
            } else{
                AuthenticationResult(
                    data = null,
                    errorMessage = "Error creating user - auth repo",
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            AuthenticationResult(
                data = null,
                errorMessage = e.message,
            )
        }
    }

    override suspend fun signIn(email: String, password: String): AuthenticationResult {
        return try {
            val user = firebaseAuth.signInWithEmailAndPassword(email, password).await().user

            val mappedUser = mapUser(user)
            _currentUser.value = mappedUser

            AuthenticationResult(
                data = mappedUser ?: User.EmptyUser,
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

    override suspend fun signOut() = firebaseAuth.signOut()

}
