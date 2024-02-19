package com.pets.breeds.adapter.database.query

import com.pets.breeds.domain.Group
import java.util.UUID

object GroupQuery {

    fun findById(id: UUID) =
        "SELECT g.* FROM groups as g WHERE id = '$id';"

    fun findByName(name: String) =
        "SELECT g.* FROM groups as g WHERE name ILIKE '%$name%';"

    fun insert(group: Group) =
        "INSERT INTO groups (id, name, description) " +
            "VALUES ('${group.id}', '${group.name}', '${group.description}');"
}
