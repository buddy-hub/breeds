package com.pets.breeds.application.result

import com.pets.breeds.domain.Group
import java.util.UUID

data class GroupResult(
    val id: UUID,
    val name: String,
    val description: String
)

fun Group.toResult(): GroupResult =
    GroupResult(
        id = id,
        name = name,
        description = description
    )
