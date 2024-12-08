package com.plenart.emotionstationcompose.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepositoryImpl
import org.koin.dsl.module

val databaseModule = module {
    single<RemoteDatabaseRepository> {
        RemoteDatabaseRepositoryImpl(
            firebaseDatabase = FirebaseFirestore.getInstance()
        )
    }
}
