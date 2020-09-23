package com.scheduling.configuration

import com.scheduling.SchedulingResource
import com.scheduling.SchedulingResourceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ResourceConfiguration {

    @Bean
    fun schedulingResource(): SchedulingResource = SchedulingResourceImpl()
}