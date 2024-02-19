package com.pets.breeds.adapter.http.request

import com.pets.breeds.application.command.SaveGroupCommand

data class SaveGroupRequest(
    val name: String,
    val description: String
)

fun SaveGroupRequest.toCommand(): SaveGroupCommand =
    SaveGroupCommand(
        name = name,
        description = description
    )
