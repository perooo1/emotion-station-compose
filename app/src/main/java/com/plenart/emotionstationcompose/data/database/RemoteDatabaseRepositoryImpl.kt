package com.plenart.emotionstationcompose.data.database

import com.google.firebase.firestore.FirebaseFirestore

class RemoteDatabaseRepositoryImpl(
    private val firebaseDatabase: FirebaseFirestore
) : RemoteDatabaseRepository {
    override fun getChildren(parentId: String?, specialistId: String?) {
        TODO("Not yet implemented")
    }
}
