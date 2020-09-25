package com.scheduling.gateway.database

import com.scheduling.core.entities.SchedulingCount
import com.scheduling.core.useCase.ListSchedulingGateway
import com.scheduling.gateway.database.mapper.SchedulingMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class ListSchedulingGatewayImpl(private val jdbcTemplate: NamedParameterJdbcTemplate) : ListSchedulingGateway {

    private val SELECT_COUNT_SCHEDULING ="""
        SELECT count(*) FROM scheduling_platform.scheduling 
            """.trimIndent();

    private val SELECT_SCHEDULING ="""
        SELECT id, despatch, beneficiary, message, type_send, status
            FROM scheduling_platform.scheduling 
            LIMIT :limit OFFSET :offset """.trimIndent();

    override fun list(limit: Int, offset: Int): SchedulingCount {
        val params =
            mapOf("limit" to limit,
            "offset" to offset)

        val count = jdbcTemplate.queryForObject(SELECT_COUNT_SCHEDULING, params, Long::class.java)

        val schedulings = jdbcTemplate.query(SELECT_SCHEDULING, params, SchedulingMapper())
        return SchedulingCount(count, schedulings)

    }
}