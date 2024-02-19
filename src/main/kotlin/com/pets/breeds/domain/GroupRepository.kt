package com.pets.breeds.domain

import java.util.UUID

interface GroupRepository {

    suspend fun findById(id: UUID): Group?

    suspend fun findByName(name: String): Group?

    suspend fun save(group: Group): Group
}
