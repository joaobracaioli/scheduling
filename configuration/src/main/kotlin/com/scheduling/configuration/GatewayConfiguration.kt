package com.scheduling.configuration

import com.scheduling.core.useCase.SaveSchedulingGateway
import com.scheduling.gateway.database.SaveSchedulingGatewayImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfiguration {

    @Bean
    fun saveScheduling(): SaveSchedulingGateway = SaveSchedulingGatewayImpl()
}