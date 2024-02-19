package com.pets.breeds.adapter.http.response

import com.pets.breeds.application.result.GroupResult
import java.util.UUID

data class GroupResponse(
    val id: UUID,
    val name: String,
    val description: String
)

fun GroupResult.toResponse(): GroupResponse =
    GroupResponse(
        id = this.id,
        name = this.name,
        description = this.description
    )