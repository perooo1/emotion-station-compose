package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class ActivityRecord(
    @PropertyName("emotionStation") val emotionStation: EmotionStation? = null,
    @PropertyName("childId") val childId: String = "",
    @PropertyName("timeOfActivity") val timeOfActivity: String = "",
    @PropertyName("recognitionAnswer1") val recognitionAnswer1: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
    @PropertyName("recognitionAnswer2") val recognitionAnswer2: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
    @PropertyName("understandingVisualAnswer1") val understandingVisualAnswer1: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
    @PropertyName("understandingVisualAnswer2") val understandingVisualAnswer2: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
    @PropertyName("understandingTextualAnswer1") val understandingTextualAnswer1: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
    @PropertyName("understandingTextualAnswer2") val understandingTextualAnswer2: ComprehensionLevel = ComprehensionLevel.INITIAL_DEFAULT,
    @PropertyName("recognitionAnswer1Duration") val recognitionAnswer1Duration: Long = 0L,
    @PropertyName("recognitionAnswer2Duration") val recognitionAnswer2Duration: Long = 0L,
    @PropertyName("understandingVisualAnswer1Duration") val understandingVisualAnswer1Duration: Long = 0L,
    @PropertyName("understandingVisualAnswer2Duration") val understandingVisualAnswer2Duration: Long = 0L,
    @PropertyName("understandingTextualAnswer1Duration") val understandingTextualAnswer1Duration: Long = 0L,
    @PropertyName("understandingTextualAnswer2Duration") val understandingTextualAnswer2Duration: Long = 0L,
)
