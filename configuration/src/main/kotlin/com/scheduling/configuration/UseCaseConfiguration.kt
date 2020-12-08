package com.scheduling.configuration

import com.scheduling.core.entities.SendRule
import com.scheduling.core.useCase.ListSchedulingGateway
import com.scheduling.core.useCase.RetrieveAllSchedulingUseCase
import com.scheduling.core.useCase.SaveSchedulingGateway
import com.scheduling.core.useCase.SaveSchedulingUseCase
import com.scheduling.core.useCase.ValidationExecutorUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {

    @Bean
    fun schedulingUseCase(
        validationExecutorUseCase: ValidationExecutorUseCase,
        saveSchedulingGateway: SaveSchedulingGateway
    ):
            SaveSchedulingUseCase = SaveSchedulingUseCase(validationExecutorUseCase, saveSchedulingGateway)

    @Bean
    fun retrieveAllSchedulingUseCase(listSchedulingGateway: ListSchedulingGateway):
            RetrieveAllSchedulingUseCase = RetrieveAllSchedulingUseCase(listSchedulingGateway)

    @Bean
    fun validationExecutor(roles: List<SendRule>): ValidationExecutorUseCase = ValidationExecutorUseCase(roles)
}
