package com.pets.breeds.application.services

import com.pets.breeds.application.command.SaveGroupCommand
import com.pets.breeds.application.command.toDomain
import com.pets.breeds.application.filter.GroupFilter
import com.pets.breeds.application.filter.toDomain
import com.pets.breeds.application.result.GroupResult
import com.pets.breeds.application.result.toResult
import com.pets.breeds.domain.GroupRepository
import com.pets.breeds.utils.pagination.PaginatedResult
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GroupService(private val groupRepository: GroupRepository) {

    suspend fun get(groupFilter: GroupFilter, page: Int, pageSize: Int): PaginatedResult<GroupResult> {
        val result = groupRepository.find(
            groupFilter = groupFilter.toDomain(),
            page = page,
            pageSize = pageSize
        )

        return PaginatedResult(
            total = result.total,
            data = result.data.map { it.toResult() }.toSet()
        )
    }

    suspend fun getById(id: UUID): GroupResult? {
        return groupRepository.findById(id)?.toResult()
    }

    suspend fun save(saveGroupCommand: SaveGroupCommand): GroupResult {
        val existentGroup = groupRepository.findByName(saveGroupCommand.name)

        if (existentGroup != null) {
            return existentGroup.toResult()
        }

        return groupRepository.save(saveGroupCommand.toDomain()).toResult()
    }
}