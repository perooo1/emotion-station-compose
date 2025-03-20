package com.plenart.emotionstationcompose.data.storage

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class RemoteStorageRepositoryImpl(private val storage: FirebaseStorage) : RemoteStorageRepository {
    override val angerStorage: StorageReference
        get() = storage.reference.child("anger")
    override val fearStorage: StorageReference
        get() = storage.reference.child("fear")
    override val happinessStorage: StorageReference
        get() = storage.reference.child("happiness")
    override val sadnessStorage: StorageReference
        get() = storage.reference.child("sadness")
}
