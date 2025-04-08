package com.plenart.emotionstationcompose.data.storage

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class RemoteStorageRepositoryImpl(private val storage: FirebaseStorage) : RemoteStorageRepository {
    override val angerStorage: StorageReference
        get() = storage.reference.child("anger")
    override val fearStorage: StorageReference
        get() = storage.reference.child("fear")
    override val happinessStorage: StorageReference
        get() = storage.reference.child("happiness")
    override val sadnessStorage: StorageReference
        get() = storage.reference.child("sadness")

    override suspend fun getRandomHappinessImage(): String {
        val images = happinessStorage.listAll().await()

        val randomImage = images.items[Random.nextInt(images.items.size)]
        return randomImage.downloadUrl.await().toString()
    }

    override suspend fun getRandomSadnessImage(): String {
        val images = sadnessStorage.listAll().await()

        val randomImage = images.items[Random.nextInt(images.items.size)]
        return randomImage.downloadUrl.await().toString()
    }

    override suspend fun getRandomAngerImage(): String {
        val images = angerStorage.listAll().await()

        val randomImage = images.items[Random.nextInt(images.items.size)]
        return randomImage.downloadUrl.await().toString()
    }

    override suspend fun getRandomFearImage(): String {
        val images = fearStorage.listAll().await()

        val randomImage = images.items[Random.nextInt(images.items.size)]
        return randomImage.downloadUrl.await().toString()
    }
}
