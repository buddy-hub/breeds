package com.pets.breeds.adapter.http.response

import com.pets.breeds.application.result.SavedGroupResult
import java.util.UUID

data class SavedGroupResponse(
    val id: UUID,
    val name: String,
    val description: String
)

fun SavedGroupResult.toResponse(): SavedGroupResponse =
    SavedGroupResponse(
        id = this.id,
        name = this.name,
        description = this.description
    )