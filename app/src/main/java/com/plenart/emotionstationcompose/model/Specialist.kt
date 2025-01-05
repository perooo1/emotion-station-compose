package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class Specialist(
    @PropertyName("additionalEducation")
    val additionalEducation: String? = null,
    @PropertyName("assignedChildren")
    val assignedChildren: List<String>? = null, // list of children's IDs
    @PropertyName("connectedParents")
    val connectedParents: List<String>? = null, // list of parents' IDs
    @PropertyName("occupation")
    val occupation: String? = null,
    @PropertyName("professionalPhoneNum")
    val professionalPhoneNum: String? = null,
    @PropertyName("workAddress")
    val workAddress: String? = null,
    @PropertyName("workHours")
    val workHours: String? = null,
    @PropertyName("id")
    override val id: String = "",
    @PropertyName("isSpecialist")
    override val isSpecialist: Boolean = true,
    @PropertyName("name")
    override val name: String = "",
    @PropertyName("lastName")
    override val lastName: String = "",
    @PropertyName("email")
    override val email: String = "",
    @PropertyName("password")
    override val password: String = ""
) : User()
