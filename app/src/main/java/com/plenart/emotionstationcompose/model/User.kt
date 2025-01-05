package com.plenart.emotionstationcompose.model

import kotlinx.serialization.Serializable

@Serializable
sealed class User {
    abstract val id: String
    abstract val isSpecialist: Boolean
    abstract val name: String
    abstract val lastName: String
    abstract val email: String
    abstract val password: String

    val isParent: Boolean
        get() = !isSpecialist

    val fullName: String?
        get() = "${name ?: ""} ${lastName ?: ""}".trim().takeIf { it.isNotEmpty() }

    data object EmptyUser : User() {
        override val id: String
            get() = "User does not exist"
        override val isSpecialist: Boolean
            get() = false
        override val name: String
            get() = "Empty user"
        override val lastName: String
            get() = "Empty user"
        override val email: String
            get() = "N/A"
        override val password: String
            get() = "N/A"
    }
}
