package com.scheduling.core.useCase

import com.scheduling.core.entities.SchedulingCount

class RetrieveAllSchedulingUseCase(
    private val listSchedulingGateway: ListSchedulingGateway) {

    fun execute(limit: Int, offset: Int): SchedulingCount {
        return listSchedulingGateway.list(limit, offset)
    }
}