package com.plenart.emotionstationcompose.model

import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable

@Serializable
open class Parent(
    @PropertyName("assignedSpecialistId")
    val assignedSpecialistId: String? = null,
    @PropertyName("children")
    val children: List<String>? = null,
    @PropertyName("specialistConnectionApproved")
    val specialistConnectionApproved: Boolean = false,
    @PropertyName("id")
    override val id: String = "",
    @PropertyName("isSpecialist")
    override val isSpecialist: Boolean = false,
    @PropertyName("name")
    override val name: String = "",
    @PropertyName("lastName")
    override val lastName: String = "",
    @PropertyName("email")
    override val email: String = "",
    @PropertyName("password")
    override val password: String = ""
) : User()
