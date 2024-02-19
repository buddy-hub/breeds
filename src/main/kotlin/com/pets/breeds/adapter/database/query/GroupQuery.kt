package com.pets.breeds.adapter.database.query

import com.pets.breeds.domain.Group
import com.pets.breeds.domain.GroupFilter
import java.util.UUID

object GroupQuery {

    fun find(groupFilter: GroupFilter, page: Int, pageSize: Int): String {
        val offset = (page - 1) * pageSize
        var query = "SELECT g.* FROM groups as g WHERE 1=1"

        with(groupFilter) {
            if (!name.isNullOrBlank()) {
                query += " AND name ILIKE '%$name%'"
            }

            if (!description.isNullOrBlank()) {
                query += " AND description ILIKE '%$description%'"
            }
        }

        query += " LIMIT $pageSize OFFSET $offset;"

        return query
    }


    fun findById(id: UUID) =
        "SELECT g.* FROM groups as g WHERE id = '$id';"

    fun findByName(name: String) =
        "SELECT g.* FROM groups as g WHERE name ILIKE '%$name%';"

    fun insert(group: Group) =
        "INSERT INTO groups (id, name, description) " +
            "VALUES ('${group.id}', '${group.name}', '${group.description}');"

    fun countTotal(groupFilter: GroupFilter): String {
        var query = "SELECT COUNT(*) FROM groups WHERE 1=1"

        with(groupFilter) {
            if (!name.isNullOrBlank()) {
                query += " AND name ILIKE '%$name%'"
            }

            if (!description.isNullOrBlank()) {
                query += " AND description ILIKE '%$description%'"
            }
        }

        query += ";"

        return query
    }
}
