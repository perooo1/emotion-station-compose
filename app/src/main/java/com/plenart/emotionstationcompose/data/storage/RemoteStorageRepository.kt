package com.plenart.emotionstationcompose.data.storage

import com.google.firebase.storage.StorageReference

interface RemoteStorageRepository {
    val angerStorage: StorageReference
    val fearStorage: StorageReference
    val happinessStorage: StorageReference
    val sadnessStorage: StorageReference

    suspend fun getRandomHappinessImage() : String
    suspend fun getRandomSadnessImage() : String
    suspend fun getRandomAngerImage() : String
    suspend fun getRandomFearImage() : String
}
