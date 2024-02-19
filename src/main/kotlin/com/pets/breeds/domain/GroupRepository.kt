package com.pets.breeds.domain

interface GroupRepository {

    suspend fun findByName(name: String): Group?

    suspend fun save(group: Group): Group
}
