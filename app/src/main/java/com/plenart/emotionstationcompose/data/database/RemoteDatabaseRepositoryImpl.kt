package com.plenart.emotionstationcompose.data.database

import com.google.firebase.FirebaseException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.snapshots
import com.plenart.emotionstationcompose.model.ActivityRecord
import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

private const val FIRESTORE_COLLECTION_ACTIVITY_RECORDS = "ActivityRecords"
private const val FIRESTORE_COLLECTION_PARENTS = "Parents"
private const val FIRESTORE_COLLECTION_SPECIALISTS = "Specialists"
private const val FIRESTORE_COLLECTION_CHILDREN = "Children"
private const val USER_ID = "id"
private const val ASSIGNED_SPECIALIST_ID = "assignedSpecialistId"
private const val PARENT_ID = "parentId"
private const val CHILD_ID = "childId"

class RemoteDatabaseRepositoryImpl(
    private val firestore: FirebaseFirestore
) : RemoteDatabaseRepository {
    override suspend fun getSpecialistFlow(specialistId: String?): Flow<Specialist?> =
        firestore.collection(FIRESTORE_COLLECTION_SPECIALISTS)
            .whereEqualTo(USER_ID, specialistId)
            .snapshots().map {
                it.documents.firstOrNull()?.toObject(Specialist::class.java)
            }

    override suspend fun getRecordedActivities(childId: String): Flow<List<ActivityRecord>> {
        return firestore.collection(FIRESTORE_COLLECTION_ACTIVITY_RECORDS)
            .whereEqualTo(CHILD_ID, childId).snapshots().map {
            it.documents.mapNotNull { document ->
                document.toObject(ActivityRecord::class.java)
            }
        }
    }

    override suspend fun getChildrenFlow(
        parentId: String?,
        specialistId: String?,
    ): Flow<List<Child>> {
        val isSpecialist: Boolean = specialistId != null
        if (parentId == null && !isSpecialist) {
            throw IllegalArgumentException("Parent id or specialist id must be provided")
        } else {
            val children = if (isSpecialist) {
                firestore.collection(FIRESTORE_COLLECTION_CHILDREN)
                    .whereEqualTo(ASSIGNED_SPECIALIST_ID, specialistId)
                    .snapshots().map {
                        it.documents.mapNotNull { document ->
                            document.toObject(Child::class.java)
                        }
                    }
            } else {
                firestore.collection(FIRESTORE_COLLECTION_CHILDREN)
                    .whereEqualTo(PARENT_ID, parentId)
                    .snapshots().map {
                        it.documents.mapNotNull { document ->
                            document.toObject(Child::class.java)
                        }
                    }
            }
            return children
        }
    }

    override suspend fun getChildFlow(childId: String): Flow<Child> {
        return firestore.collection(FIRESTORE_COLLECTION_CHILDREN).document(childId).snapshots()
            .map {
                it.toObject(Child::class.java)!!
            }
    }


    override suspend fun getParentFromDatabase(id: String): Parent? {
        try {
            var obj: Parent? = null
            val snapshot =
                firestore.collection(FIRESTORE_COLLECTION_PARENTS).document(id).get().await()
            if (snapshot.data != null) {
                obj = snapshot.toObject(Parent::class.java)
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
                obj = snapshot.toObject(Specialist::class.java)
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

    override suspend fun registerUserInDatabase(user: User) {
        val collection =
            if (user.isSpecialist) {
                FIRESTORE_COLLECTION_SPECIALISTS
            } else {
                FIRESTORE_COLLECTION_PARENTS
            }

        try {
            firestore.collection(collection)
                .document(user.id)
                .set(user, SetOptions.merge())
                .await()
        } catch (e: FirebaseException) {
            e.printStackTrace()
        }
    }
}
