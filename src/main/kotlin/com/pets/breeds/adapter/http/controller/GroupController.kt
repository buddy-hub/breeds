package com.pets.breeds.adapter.http.controller

import com.pets.breeds.adapter.http.request.SaveGroupRequest
import com.pets.breeds.adapter.http.request.toCommand
import com.pets.breeds.adapter.http.response.SavedGroupResponse
import com.pets.breeds.adapter.http.response.toResponse
import com.pets.breeds.application.services.GroupService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/groups")
class GroupController(val groupService: GroupService) {

    @PostMapping
    suspend fun save(@RequestBody saveGroupRequest: SaveGroupRequest): SavedGroupResponse {
        return groupService.save(saveGroupRequest.toCommand()).toResponse()
    }
}
