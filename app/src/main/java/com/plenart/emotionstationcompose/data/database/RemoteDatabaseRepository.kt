package com.plenart.emotionstationcompose.data.database


interface RemoteDatabaseRepository {
    fun getChildren(parentId: String?, specialistId: String?)
}
