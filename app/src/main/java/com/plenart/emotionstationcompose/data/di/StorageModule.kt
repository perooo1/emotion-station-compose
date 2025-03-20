package com.plenart.emotionstationcompose.data.di

import com.google.firebase.storage.FirebaseStorage
import com.plenart.emotionstationcompose.data.storage.RemoteStorageRepository
import com.plenart.emotionstationcompose.data.storage.RemoteStorageRepositoryImpl
import org.koin.dsl.module

val storageModule = module {
    single<RemoteStorageRepository> {
        RemoteStorageRepositoryImpl(
            storage = FirebaseStorage.getInstance()
        )
    }
}
