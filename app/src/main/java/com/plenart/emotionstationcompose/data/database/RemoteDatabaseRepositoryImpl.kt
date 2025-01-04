package com.plenart.emotionstationcompose.data.database

import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import kotlinx.coroutines.tasks.await

private const val FIRESTORE_COLLECTION_PARENTS = "Parents"
private const val FIRESTORE_COLLECTION_SPECIALISTS = "Specialists"

class RemoteDatabaseRepositoryImpl(
    private val firestore: FirebaseFirestore
) : RemoteDatabaseRepository {
    override suspend fun getParentFromDatabase(id: String): Parent? {
        try {
            var obj: Parent? = null
            val snapshot =
                firestore.collection(FIRESTORE_COLLECTION_PARENTS).document(id).get().await()
            if (snapshot.data != null) {
                obj = snapshot.toObject(Parent::class.java)!!
            }
            return obj
        } catch (e: FirebaseException) {
            e.printStackTrace()
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun getSpecialistFromDatabase(id: String): Specialist? {
        try {
            var obj: Specialist? = null
            val snapshot =
                firestore.collection(FIRESTORE_COLLECTION_SPECIALISTS).document(id).get().await()
            if (snapshot.data != null) {
                obj = snapshot.toObject(Specialist::class.java)!!
            }
            return obj
        } catch (e: FirebaseException) {
            e.printStackTrace()
            return null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
