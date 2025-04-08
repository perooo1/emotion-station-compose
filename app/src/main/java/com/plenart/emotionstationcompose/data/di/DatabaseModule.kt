package com.plenart.emotionstationcompose.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.emotionstationcompose.data.database.RemoteActivityQuestionRepository
import com.plenart.emotionstationcompose.data.database.RemoteActivityQuestionRepositoryImpl
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepositoryImpl
import org.koin.dsl.module

val databaseModule = module {
    single<RemoteDatabaseRepository> {
        RemoteDatabaseRepositoryImpl(
            firestore = FirebaseFirestore.getInstance()
        )
    }
    single<RemoteActivityQuestionRepository> {
        RemoteActivityQuestionRepositoryImpl(
            firestore = FirebaseFirestore.getInstance()
        )
    }
}
