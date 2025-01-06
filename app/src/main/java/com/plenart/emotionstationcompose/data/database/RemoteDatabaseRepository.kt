package com.plenart.emotionstationcompose.data.database

import com.google.firebase.firestore.QuerySnapshot
import com.plenart.emotionstationcompose.model.Parent
import com.plenart.emotionstationcompose.model.Specialist
import com.plenart.emotionstationcompose.model.User
import kotlinx.coroutines.flow.Flow

interface RemoteDatabaseRepository {
    //Flows
    suspend fun getSpecialistFlow(specialistId: String?) : Flow<QuerySnapshot>

    suspend fun getParentFromDatabase(id: String): Parent?
    suspend fun getSpecialistFromDatabase(id: String): Specialist?
    suspend fun registerUserInDatabase(user: User)
}
