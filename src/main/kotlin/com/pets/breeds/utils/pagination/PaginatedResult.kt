package com.pets.breeds.utils.pagination

data class PaginatedResult<T>(
    val total: Int,
    val data: Set<T>
)
