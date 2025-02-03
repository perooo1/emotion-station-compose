package com.plenart.emotionstationcompose.data.database

import com.plenart.emotionstationcompose.model.Child
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User
import kotlinx.coroutines.flow.Flow

interface RemoteDatabaseRepository {
    //Flows
    suspend fun getChildFlow(childId: String): Flow<Child>
    suspend fun getChildrenFlow(parentId: String?, specialistId: String?) : Flow<List<Child>>
    suspend fun getSpecialistFlow(specialistId: String?) : Flow<Specialist?>

    suspend fun getParentFromDatabase(id: String): Parent?
    suspend fun getSpecialistFromDatabase(id: String): Specialist?
    suspend fun registerUserInDatabase(user: User)
}
