package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class Child(
    @PropertyName("id")
    val id: String,
    @PropertyName("parentId")
    val parentId: String,
    @PropertyName("assignedSpecialistId")
    val assignedSpecialistId: String? = null,
    @PropertyName("name")
    val name: String,
    @PropertyName("lastName")
    val lastName: String,
    @PropertyName("age")
    val age: Int,
    @PropertyName("isGenderMale")
    val isGenderMale: Boolean,
    @PropertyName("diagnosis")
    val diagnosis: String,
    @PropertyName("emotionForecast")
    val emotionForecast: Map<@Contextual LocalDate, EmotionForecast>? = null,
    @PropertyName("attendsKindergarten")
    val attendsKindergarten: Boolean = false,
    @PropertyName("riskyPregnancy")
    val riskyPregnancy: Boolean = false,
    @PropertyName("pregnancyBirthWeek")
    val pregnancyBirthWeek: Int? = null,
    @PropertyName("treatmentStartMonth")
    val treatmentStartMonth: @Contextual LocalDate? = null,
    @PropertyName("specialistNote")
    val specialistNote: String? = null
) {
    val fullName: String
        get() = "$name $lastName"
    val isGenderFemale: Boolean
        get() = !isGenderMale
}
