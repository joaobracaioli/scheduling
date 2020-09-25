package com.scheduling.controllers.translators

import com.scheduling.api.model.SchedulingRequestDTO
import com.scheduling.core.entities.Scheduling
import com.scheduling.core.entities.SendType
import com.scheduling.core.entities.Status
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class SchedulingDTOTranslateToScheduling {

    companion object {
        fun translate(scheduling: SchedulingRequestDTO): Scheduling {

            return Scheduling(
                beneficiary = scheduling.beneficiary ,
                despatch = LocalDateTime.of(LocalDate.parse(scheduling.dateSend), LocalTime.parse(scheduling.hourSend)),
                message =  scheduling.message,
                sendType =  SendType.valueOf(scheduling.typeSend),
                status = Status.CREATED
            )
        }
    }
}