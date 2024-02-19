package com.pets.breeds.application.command

import com.pets.breeds.domain.Group
import java.util.UUID

data class SaveGroupCommand(
    val name: String,
    val description: String
)

fun SaveGroupCommand.toDomain(): Group =
    Group(
        id = UUID.randomUUID(),
        name = this.name,
        description = this.description
    )
