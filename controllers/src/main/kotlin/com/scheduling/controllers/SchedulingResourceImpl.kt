package com.scheduling.controllers

import com.scheduling.api.SchedulingResource
import com.scheduling.api.model.SchedulingDTO
import com.scheduling.controllers.translators.SchedulingDTOTranslateToScheduling
import com.scheduling.core.useCase.SaveSchedulingUseCase
import org.springframework.web.bind.annotation.RestController

@RestController
class SchedulingResourceImpl(
    private val saveSchedulingUseCase: SaveSchedulingUseCase) : SchedulingResource {
    override fun add(scheduling: SchedulingDTO) {
        saveSchedulingUseCase.execute(SchedulingDTOTranslateToScheduling.translate(scheduling))
    }
}