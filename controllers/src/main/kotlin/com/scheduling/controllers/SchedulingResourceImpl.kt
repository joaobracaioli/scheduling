package com.scheduling.controllers

import com.scheduling.api.SchedulingResource
import com.scheduling.api.model.SchedulingCountDTO
import com.scheduling.api.model.SchedulingRequestDTO
import com.scheduling.controllers.translators.SchedulingCountTranslateToSchedulingCountDTO
import com.scheduling.controllers.translators.SchedulingRequestDTOTranslateToScheduling
import com.scheduling.core.useCase.RetrieveAllSchedulingUseCase
import com.scheduling.core.useCase.SaveSchedulingUseCase
import org.springframework.web.bind.annotation.RestController
import java.security.InvalidParameterException

@RestController
class SchedulingResourceImpl(
    private val saveSchedulingUseCase: SaveSchedulingUseCase,
    private val retrieveAllSchedulingUseCase: RetrieveAllSchedulingUseCase
) : SchedulingResource {
    private val MAX_SCHEDULING_TO_RETURN : Int = 5000
    override fun add(scheduling: SchedulingRequestDTO) {
        saveSchedulingUseCase.execute(SchedulingRequestDTOTranslateToScheduling.translate(scheduling))
    }

    override fun list(limit: Int, offset: Int): SchedulingCountDTO {
        if (limit > MAX_SCHEDULING_TO_RETURN)
            throw InvalidParameterException("Maximum value of scheduling to return is " + MAX_SCHEDULING_TO_RETURN)
        return SchedulingCountTranslateToSchedulingCountDTO.translate(
            retrieveAllSchedulingUseCase.execute(
                limit,
                offset
            )
        )
    }
}