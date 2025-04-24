package com.plenart.emotionstationcompose.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.StorageReference
import com.plenart.emotionstationcompose.data.database.ANGER
import com.plenart.emotionstationcompose.data.database.RemoteActivityQuestionRepository
import com.plenart.emotionstationcompose.data.database.RemoteDatabaseRepository
import com.plenart.emotionstationcompose.data.storage.RemoteStorageRepository
import com.plenart.emotionstationcompose.model.ActivityRecord
import com.plenart.emotionstationcompose.model.ActivityType
import com.plenart.emotionstationcompose.model.ComprehensionLevel
import com.plenart.emotionstationcompose.model.EmotionStation
import com.plenart.emotionstationcompose.model.ObservationCategory
import com.plenart.emotionstationcompose.model.Question
import com.plenart.emotionstationcompose.ui.activity.mapper.EmotionStationActivityMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime

//TODO pass what emotion was selected
class EmotionStationActivityViewModel(
    selectedChildId: String,
    private val databaseRepository: RemoteDatabaseRepository,
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
                        if (it.observationCategory != ObservationCategory.EMOTION_UNDERSTANDING_TEXTUAL) {
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

    fun onOptionSelected(
        questionIndex: Int,
        optionIndex: Int,
        selectedOptionComprehensionLevel: ComprehensionLevel,
    ) {
        _uiState.update { currentState ->
            val updatedQuestions = currentState.questions.mapIndexed { index, question ->
                if (index == questionIndex) {
                    mapper.updateSelectedOption(
                        question = question,
                        selectedOptionIndex = optionIndex,
                        selectedOptionComprehensionLevel = selectedOptionComprehensionLevel,
                        answeredTimeMillis = System.currentTimeMillis()
                    )
                } else question
            }
            mapper.toActivityUiState(currentState.selectedChildId, updatedQuestions)
        }
    }

    fun onQuestionVisible(index: Int) {
        _uiState.update { currentState ->
            val updatedQuestions = currentState.questions.mapIndexed { i, question ->
                if (i == index && question.startTimeMillis == null) {
                    mapper.updateQuestionStartTime(
                        question = question,
                        startTimeMillis = System.currentTimeMillis()
                    )
                } else question
            }
            mapper.toActivityUiState(currentState.selectedChildId, updatedQuestions)
        }
    }

    fun recordActivity() {
        val questions = _uiState.value.questions
        val q1 = questions[0]
        val q2 = questions[1]
        val q3 = questions[2]
        val q4 = questions[3]
        val q5 = questions[4]
        val q6 = questions[5]

        val activityRecord = ActivityRecord(
            childId = _uiState.value.selectedChildId,
            emotionStation = EmotionStation(
                //TODO add true emotion station
                activityType = ActivityType.STATION_OF_ANGER,
                stationName = ActivityType.STATION_OF_ANGER.name,
                questions = _uiState.value.questions.map {
                    Question(
                        activityType = ActivityType.STATION_OF_ANGER,
                        text = it.text,
                        storyText = it.storyText,
                        options = it.options,
                        imageAssetPath = it.imageUrl,
                        observationCategory = it.observationCategory
                    )
                }.toList(),
            ),
            timeOfActivity = LocalDateTime.now().toString(),
            recognitionAnswer1 = q1.selectedComprehensionLevel,
            recognitionAnswer2 = q2.selectedComprehensionLevel,
            understandingVisualAnswer1 = q5.selectedComprehensionLevel,
            understandingVisualAnswer2 = q6.selectedComprehensionLevel,
            understandingTextualAnswer1 = q3.selectedComprehensionLevel,
            understandingTextualAnswer2 = q4.selectedComprehensionLevel,
            recognitionAnswer1Duration = (q1.answeredTimeMillis?.minus(q1.startTimeMillis ?: 0L)
                ?: 0L),
            recognitionAnswer2Duration = (q2.answeredTimeMillis?.minus(q2.startTimeMillis ?: 0L)
                ?: 0L),
            understandingVisualAnswer1Duration = (q5.answeredTimeMillis?.minus(
                q5.startTimeMillis ?: 0L
            ) ?: 0L),
            understandingVisualAnswer2Duration = (q6.answeredTimeMillis?.minus(
                q6.startTimeMillis ?: 0L
            ) ?: 0L),
            understandingTextualAnswer1Duration = (q3.answeredTimeMillis?.minus(
                q3.startTimeMillis ?: 0L
            ) ?: 0L),
            understandingTextualAnswer2Duration = (q4.answeredTimeMillis?.minus(
                q4.startTimeMillis ?: 0L
            ) ?: 0L),
        )

        viewModelScope.launch {
            databaseRepository.recordCompletedActivity(activityRecord)
        }
        _uiState.update {
            mapper.updateActivityCompleted(currentUiState = uiState.value)
        }
    }
}
