package com.plenart.emotionstationcompose.data.database

import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist

interface RemoteDatabaseRepository {
    suspend fun getParentFromDatabase(id: String): Parent?
    suspend fun getSpecialistFromDatabase(id: String): Specialist?
}
