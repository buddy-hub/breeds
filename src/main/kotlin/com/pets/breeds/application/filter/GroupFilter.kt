package com.pets.breeds.application.filter
import com.pets.breeds.domain.GroupFilter as GroupFilterDomain

data class GroupFilter(
    val name: String?,
    val description: String?
)

fun GroupFilter.toDomain(): GroupFilterDomain =
    GroupFilterDomain(
        name = this.name,
        description = this.description
    )
