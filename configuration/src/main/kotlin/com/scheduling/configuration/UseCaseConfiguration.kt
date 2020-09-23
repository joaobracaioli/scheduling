package com.scheduling.configuration

import com.scheduling.core.useCase.SaveSchedulingGateway
import com.scheduling.core.useCase.SaveSchedulingUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun schedulingUseCase(saveSchedulingGateway: SaveSchedulingGateway): SaveSchedulingUseCase = SaveSchedulingUseCase(saveSchedulingGateway)
}