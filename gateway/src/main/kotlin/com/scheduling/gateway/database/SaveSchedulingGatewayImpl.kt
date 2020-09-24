package com.scheduling.gateway.database

import com.scheduling.core.entities.Scheduling
import com.scheduling.core.useCase.SaveSchedulingGateway
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.HashMap

class SaveSchedulingGatewayImpl(private val jdbcTemplate: NamedParameterJdbcTemplate) : SaveSchedulingGateway {

    private val INSERT_SCHEDULING =
        "INSERT INTO scheduling_platform.scheduling (created_at, updated_at, despatch, beneficiary, message, status) " +
                "VALUES (now(), now(), :despatch, :beneficiary, :message, :status)";


    override fun save(scheduling: Scheduling) {
        val params: MutableMap<String, Any> = HashMap()
        params["despatch"] = scheduling.despatch
        params["beneficiary"] = scheduling.beneficiary
        params["message"] = scheduling.message
        params["status"] = scheduling.status.name
        jdbcTemplate.update(
            INSERT_SCHEDULING,
            params
        )
    }
}
