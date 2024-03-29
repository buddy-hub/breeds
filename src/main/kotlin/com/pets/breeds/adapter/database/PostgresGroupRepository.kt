package com.pets.breeds.adapter.database

import com.pets.breeds.adapter.database.query.GroupQuery
import com.pets.breeds.adapter.database.rowMapper.GroupRowMapper
import com.pets.breeds.domain.Group
import com.pets.breeds.domain.GroupFilter
import com.pets.breeds.domain.GroupRepository
import com.pets.breeds.utils.pagination.PaginatedResult
import com.pets.breeds.utils.pagination.Pagination
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PostgresGroupRepository(@Autowired val jdbcTemplate: JdbcTemplate): GroupRepository {

    private val rowMapper: GroupRowMapper = GroupRowMapper();
    override suspend fun find(
        groupFilter: GroupFilter,
        pagination: Pagination
    ): PaginatedResult<Group> {
        val total = this.countTotal(groupFilter);
        val data = jdbcTemplate.query(
            GroupQuery.find(
                groupFilter = groupFilter,
                page = pagination.page,
                pageSize = pagination.pageSize
            ), rowMapper
        )

        return PaginatedResult(total = total, data = data.toSet())
    }

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

    private suspend fun countTotal(groupFilter: GroupFilter): Int {
        return jdbcTemplate.queryForObject(
            GroupQuery.countTotal(groupFilter),
            Int::class.java
        ) ?: 0
    }
}