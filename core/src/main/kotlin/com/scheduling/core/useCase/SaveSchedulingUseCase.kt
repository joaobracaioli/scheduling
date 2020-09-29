package com.scheduling.core.useCase

import com.scheduling.core.entities.Scheduling

class SaveSchedulingUseCase (
    private val validationExecutorUseCase: ValidationExecutorUseCase,
    private val saveSchedulingGateway: SaveSchedulingGateway
) {

    public fun execute(scheduling: Scheduling) {
        if (validationExecutorUseCase.execute(scheduling)) {
            saveSchedulingGateway.save(scheduling)
            return
        }
        throw NotImplementedError()
    }
}