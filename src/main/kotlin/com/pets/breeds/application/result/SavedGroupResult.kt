package com.pets.breeds.application.result

import com.pets.breeds.domain.Group
import java.util.UUID

data class SavedGroupResult(
    val id: UUID,
    val name: String,
    val description: String
)

fun Group.toResult(): SavedGroupResult =
    SavedGroupResult(
        id = id,
        name = name,
        description = description
    )
