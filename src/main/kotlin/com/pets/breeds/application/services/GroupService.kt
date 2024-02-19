package com.pets.breeds.application.services

import com.pets.breeds.application.command.SaveGroupCommand
import com.pets.breeds.application.command.toDomain
import com.pets.breeds.application.result.GroupResult
import com.pets.breeds.application.result.toResult
import com.pets.breeds.domain.GroupRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GroupService(val groupRepository: GroupRepository) {

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