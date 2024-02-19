package com.pets.breeds.domain

import com.pets.breeds.utils.pagination.PaginatedResult
import com.pets.breeds.utils.pagination.Pagination
import java.util.UUID

interface GroupRepository {

    suspend fun find(
        groupFilter: GroupFilter,
        pagination: Pagination
    ): PaginatedResult<Group>

    suspend fun findById(id: UUID): Group?

    suspend fun findByName(name: String): Group?

    suspend fun save(group: Group): Group
}
