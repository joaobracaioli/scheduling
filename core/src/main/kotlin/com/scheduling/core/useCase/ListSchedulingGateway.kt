package com.scheduling.core.useCase

import com.scheduling.core.entities.SchedulingCount

interface ListSchedulingGateway {

    fun list(limit: Int, offset: Int): SchedulingCount
}