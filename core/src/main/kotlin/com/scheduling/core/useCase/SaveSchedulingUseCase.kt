package com.scheduling.core.useCase

import com.scheduling.core.entities.Scheduling

class SaveSchedulingUseCase (
    private val saveSchedulingGateway: SaveSchedulingGateway
) {

    public fun execute(scheduling: Scheduling) {
        saveSchedulingGateway.save(scheduling);
    }
}