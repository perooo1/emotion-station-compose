package com.plenart.emotionstationcompose.data.database

import com.google.firebase.firestore.FirebaseFirestore
import com.plenart.emotionstationcompose.model.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import java.util.Locale

private const val FIRESTORE_COLLECTION_QUESTIONS = "questions"

const val BASIC_RECOGNITION = "basic_recognition"
const val BASIC_VISUAL = "basic_visual"
const val BASIC_TEXTUAL = "basic_textual"
const val COMPLEX_RECOGNITION = "complex_recognition"
const val COMPLEX_VISUAL = "complex_visual"
const val COMPLEX_TEXTUAL = "complex_textual"

const val ANGER = "anger"
const val FEAR = "fear"
const val HAPPINESS = "happiness"
const val SADNESS = "sadness"

class RemoteActivityQuestionRepositoryImpl(
    private val firestore: FirebaseFirestore,
) : RemoteActivityQuestionRepository {

    override suspend fun getQuestions(emotion: String): Flow<List<Question>> {

        val observationCategories = listOf(
            BASIC_RECOGNITION,
            COMPLEX_RECOGNITION,
            BASIC_TEXTUAL,
            COMPLEX_TEXTUAL,
            BASIC_VISUAL,
            COMPLEX_VISUAL,
        )

        val flows = observationCategories.map {
            getQuestionsByCategory(emotion, it)
        }

        return combine(flows) { allQuestions ->
            allQuestions.mapNotNull { it.randomOrNull() }
        }.flowOn(Dispatchers.IO)
    }

    private fun getQuestionsByCategory(emotion: String, category: String): Flow<List<Question>> =
        callbackFlow<List<Question>> {
            val docPath = "${emotion}_$category"

            val listener = firestore
                .collection(
                    FIRESTORE_COLLECTION_QUESTIONS
                        .replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }
                ).document(docPath)
                .collection(FIRESTORE_COLLECTION_QUESTIONS)
                .addSnapshotListener { snapshot, error ->

                    if (error != null) {
                        close(error)
                        return@addSnapshotListener
                    }

                    val questions = snapshot?.documents?.mapNotNull {
                        it.toObject(Question::class.java)
                    } ?: emptyList()

                    trySend(questions).isSuccess
                }
            awaitClose { listener.remove() }
        }.flowOn(Dispatchers.IO)
}
