package com.pets.breeds.adapter.database.rowMapper

import com.pets.breeds.domain.Group
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.UUID

class GroupRowMapper: RowMapper<Group> {

    override fun mapRow(rs: ResultSet, rowNum: Int): Group? =
        Group(
            id = UUID.fromString(rs.getString("id")),
            name = rs.getString("name"),
            description = rs.getString("description")
        )
}