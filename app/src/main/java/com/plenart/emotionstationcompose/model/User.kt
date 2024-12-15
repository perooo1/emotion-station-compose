package com.plenart.emotionstationcompose.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val isSpecialist: Boolean = false,
    val name: String? = null,
    val lastName: String? = null,
    val email: String? = null,
    val password: String? = null,
){
    val isEmpty: Boolean
        get() = this == empty

    val isNotEmpty: Boolean
        get() = !isEmpty

    val isParent: Boolean
        get() = !isSpecialist

    val fullName : String?
            get() = "${name ?: ""} ${lastName ?: ""}".trim().takeIf { it.isNotEmpty() }

    companion object{
        val empty = User(id = "")
    }
}



