package com.scheduling.configuration

import com.scheduling.core.useCase.ListSchedulingGateway
import com.scheduling.core.useCase.SaveSchedulingGateway
import com.scheduling.gateway.database.ListSchedulingGatewayImpl
import com.scheduling.gateway.database.SaveSchedulingGatewayImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

@Configuration
class GatewayConfiguration {

    @Bean
    fun saveScheduling(jdbcTemplate: NamedParameterJdbcTemplate):
            SaveSchedulingGateway = SaveSchedulingGatewayImpl(jdbcTemplate)

    @Bean
    fun listScheduling(jdbcTemplate: NamedParameterJdbcTemplate):
            ListSchedulingGateway = ListSchedulingGatewayImpl(jdbcTemplate)
}