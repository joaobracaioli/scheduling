package com.scheduling.controllers.translators

import com.scheduling.api.model.SchedulingCountDTO
import com.scheduling.core.entities.SchedulingCount

class SchedulingCountTranslateToSchedulingCountDTO {
    companion object {
        fun translate(schedulingCount: SchedulingCount): SchedulingCountDTO {
            return SchedulingCountDTO(
                total = schedulingCount.total,
                schedulings = SchedulingTranslateToSchedulingDTO.translate(schedulingCount.schedulings)
            )
        }
    }
}