package com.plenart.emotionstationcompose.data.storage

import com.google.firebase.storage.StorageReference

interface RemoteStorageRepository {
    val angerStorage: StorageReference
    val fearStorage: StorageReference
    val happinessStorage: StorageReference
    val sadnessStorage: StorageReference
}
