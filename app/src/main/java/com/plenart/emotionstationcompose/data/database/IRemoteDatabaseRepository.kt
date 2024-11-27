package com.plenart.emotionstationcompose.data.database

import kotlinx.coroutines.flow.Flow

interface IRemoteDatabaseRepository {
    fun getChildren(parentId: String?, specialistId: String?)
}
