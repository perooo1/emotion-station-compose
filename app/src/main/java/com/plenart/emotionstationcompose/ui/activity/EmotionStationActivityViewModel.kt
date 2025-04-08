package com.plenart.emotionstationcompose.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.StorageReference
import com.plenart.emotionstationcompose.data.database.ANGER
import com.plenart.emotionstationcompose.data.database.RemoteActivityQuestionRepository
import com.plenart.emotionstationcompose.data.storage.RemoteStorageRepository
import com.plenart.emotionstationcompose.ui.activity.mapper.EmotionStationActivityMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

const val categoryUnderstandingTextual = "emotionUnderstandingTextual"

//TODO pass what emotion was selected
class EmotionStationActivityViewModel(
    selectedChildId: String,
    private val storageRepository: RemoteStorageRepository,
    private val questionRepository: RemoteActivityQuestionRepository,
    private val mapper: EmotionStationActivityMapper,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmotionStationActivityUiState(selectedChildId))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            questionRepository.getQuestions(ANGER).collect { questions ->
                val questionUiModels = questions.map {
                    if (!it.imageAssetPath.isNullOrEmpty()) {
                        val imgReference = getImageReference(it.imageAssetPath)
                        val imageUrl = imgReference.downloadUrl.await().toString()

                        mapper.toQuestionUiState(it, imageUrl)
                    } else {
                        if (it.observationCategory.name != categoryUnderstandingTextual) {
                            val randomImage = getRandomImage()

                            mapper.toQuestionUiState(it, randomImage)
                        } else {

                            mapper.toQuestionUiState(it, null)
                        }
                    }
                }

                _uiState.update {
                    mapper.toActivityUiState(
                        childId = it.selectedChildId,
                        questions = questionUiModels
                    )
                }
            }
        }
    }

    private suspend fun getRandomImage(): String {
       return storageRepository.getRandomAngerImage()
    }

    //TODO pass type of storage
    private suspend fun getImageReference(image: String): StorageReference {
        return storageRepository.angerStorage.child(image)
    }
}
