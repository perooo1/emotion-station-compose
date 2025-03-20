package com.plenart.emotionstationcompose.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.StorageReference
import com.plenart.emotionstationcompose.data.storage.RemoteStorageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EmotionStationActivityViewModel(
    selectedChildId: String,
    private val storageRepository: RemoteStorageRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(EmotionStationActivityUiState(selectedChildId))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            //val a = getImage("fear/fear_nervous.webp")
            val a = getImage("anger_basic2.webp")
            val b = a.downloadUrl.await().toString()
            _uiState.update {
                it.copy(imageUrl = b)
            }
        }
    }

    suspend fun getImage(image: String): StorageReference {
        return storageRepository.angerStorage.child(image)
    }
}
