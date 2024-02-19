package com.pets.breeds.adapter.database

import com.pets.breeds.adapter.database.query.GroupQuery
import com.pets.breeds.adapter.database.rowMapper.GroupRowMapper
import com.pets.breeds.domain.Group
import com.pets.breeds.domain.GroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PostgresGroupRepository(@Autowired val jdbcTemplate: JdbcTemplate): GroupRepository {

    private val rowMapper: GroupRowMapper = GroupRowMapper();
    override suspend fun findById(id: UUID): Group? {
        return try {
            jdbcTemplate.queryForObject(GroupQuery.findById(id), rowMapper)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    override suspend fun findByName(name: String): Group? {
        return try {
            jdbcTemplate.queryForObject(GroupQuery.findByName(name), rowMapper)
        } catch (e: EmptyResultDataAccessException) {
            null
        }
    }

    override suspend fun save(group: Group): Group {
        jdbcTemplate.update(GroupQuery.insert(group))
        return group
    }
}