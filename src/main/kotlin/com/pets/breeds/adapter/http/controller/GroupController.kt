package com.pets.breeds.adapter.http.controller

import com.pets.breeds.adapter.http.request.SaveGroupRequest
import com.pets.breeds.adapter.http.request.toCommand
import com.pets.breeds.adapter.http.response.GroupResponse
import com.pets.breeds.adapter.http.response.toResponse
import com.pets.breeds.application.services.GroupService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/groups")
class GroupController(val groupService: GroupService) {

    @GetMapping("/{id}")
    suspend fun getById(@PathVariable id: String): GroupResponse? {
        return groupService.getById(UUID.fromString(id))?.toResponse()
    }

    @PostMapping
    suspend fun save(@RequestBody saveGroupRequest: SaveGroupRequest): GroupResponse {
        return groupService.save(saveGroupRequest.toCommand()).toResponse()
    }
}
