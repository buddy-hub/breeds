package com.pets.breeds.application.services

import com.pets.breeds.application.command.SaveGroupCommand
import com.pets.breeds.application.command.toDomain
import com.pets.breeds.application.result.SavedGroupResult
import com.pets.breeds.application.result.toResult
import com.pets.breeds.domain.GroupRepository
import org.springframework.stereotype.Service

@Service
class GroupService(val groupRepository: GroupRepository) {

    suspend fun save(saveGroupCommand: SaveGroupCommand): SavedGroupResult {
        val existentGroup = groupRepository.findByName(saveGroupCommand.name)

        if (existentGroup != null) {
            return existentGroup.toResult()
        }

        return groupRepository.save(saveGroupCommand.toDomain()).toResult()
    }

//    suspend fun save(saveGroupCommand: SaveGroupCommand): SavedGroupResult {
//        println("Chegou no service")
//        return SavedGroupResult(
//            id = UUID.randomUUID(),
//            name = "Mock",
//            description = "Mock group"
//        )
//    }
}