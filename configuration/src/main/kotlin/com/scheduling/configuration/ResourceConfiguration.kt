package com.scheduling.configuration

import com.scheduling.api.SchedulingResource
import com.scheduling.controllers.SchedulingResourceImpl
import com.scheduling.core.useCase.SaveSchedulingUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ResourceConfiguration {

    @Bean
    fun schedulingResource(saveSchedulingUseCase: SaveSchedulingUseCase):
            SchedulingResource = SchedulingResourceImpl(saveSchedulingUseCase)
}