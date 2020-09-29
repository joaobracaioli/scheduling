package com.scheduling.core.useCase

import com.scheduling.core.entities.Scheduling
import com.scheduling.core.entities.SendRule

class ValidationExecutorUseCase(
    private val roles: List<SendRule>
) {

    public fun execute(scheduling: Scheduling): Boolean {
        return roles.all { it -> it.validate(scheduling) }
    }


}