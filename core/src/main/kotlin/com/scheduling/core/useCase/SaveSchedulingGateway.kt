package com.scheduling.core.useCase

import com.scheduling.core.entities.Scheduling

interface SaveSchedulingGateway {

    fun save(scheduling: Scheduling)
}