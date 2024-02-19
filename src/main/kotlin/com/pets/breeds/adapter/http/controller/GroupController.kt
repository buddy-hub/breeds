package com.pets.breeds.adapter.http.controller

import com.pets.breeds.adapter.http.request.SaveGroupRequest
import com.pets.breeds.adapter.http.request.toCommand
import com.pets.breeds.adapter.http.response.GroupResponse
import com.pets.breeds.adapter.http.response.toResponse
import com.pets.breeds.application.filter.GroupFilter
import com.pets.breeds.application.services.GroupService
import com.pets.breeds.utils.pagination.PaginatedResult
import com.pets.breeds.utils.pagination.Pagination
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/v1/groups")
class GroupController(val groupService: GroupService) {

    @GetMapping
    suspend fun get(
        @RequestParam page: Int?,
        @RequestParam(name = "page_size") pageSize: Int?,
        @RequestParam name: String?,
        @RequestParam description: String?
    ): PaginatedResult<GroupResponse> {
        val groupFilter = GroupFilter(name, description)
        val pagination = Pagination(
            page = page ?: 1,
            pageSize = pageSize ?: 10
        )

        val result = groupService.get(
            groupFilter = groupFilter,
            pagination = pagination
        )

        return PaginatedResult(
            total = result.total,
            data = result.data.map { it.toResponse() }.toSet()
        )
    }

    @GetMapping("/{id}")
    suspend fun getById(@PathVariable id: String): GroupResponse? {
        return groupService.getById(UUID.fromString(id))?.toResponse()
    }

    @PostMapping
    suspend fun save(@RequestBody saveGroupRequest: SaveGroupRequest): GroupResponse {
        return groupService.save(saveGroupRequest.toCommand()).toResponse()
    }
}
