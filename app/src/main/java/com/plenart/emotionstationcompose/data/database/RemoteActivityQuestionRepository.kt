package com.plenart.emotionstationcompose.data.database

import com.plenart.emotionstationcompose.model.Question
import kotlinx.coroutines.flow.Flow

interface RemoteActivityQuestionRepository {
    suspend fun getQuestions(emotion:String):Flow<List<Question>>
    //suspend fun getQuestions(subCollection: String): Flow<List<Question>>
}
