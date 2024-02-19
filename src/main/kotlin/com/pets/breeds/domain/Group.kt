package com.pets.breeds.domain

import java.util.UUID

data class Group(
    val id: UUID,
    val name: String,
    val description: String
)
