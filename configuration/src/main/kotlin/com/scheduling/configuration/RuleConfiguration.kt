package com.scheduling.configuration

import com.scheduling.core.entities.SendRule
import com.scheduling.core.entities.role.Email
import com.scheduling.core.entities.role.SMS
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RuleConfiguration {

    @Bean
    fun roleEmail() : SendRule = Email()

    @Bean
    fun roleSMS(): SendRule = SMS()
}