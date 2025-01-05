package com.plenart.emotionstationcompose.data.database

import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User

interface RemoteDatabaseRepository {
    suspend fun getParentFromDatabase(id: String): Parent?
    suspend fun getSpecialistFromDatabase(id: String): Specialist?
    suspend fun registerUserInDatabase(user: User)
}
