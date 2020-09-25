package com.scheduling.controllers.translators

import com.scheduling.api.model.SchedulingResponseDTO
import com.scheduling.core.entities.Scheduling
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

class SchedulingTranslateToSchedulingDTO {
    companion object {
        fun translate(scheduling: Scheduling): SchedulingResponseDTO {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return SchedulingResponseDTO(
                beneficiary = scheduling.beneficiary,
                despatch = scheduling.despatch.format(formatter),
                message = scheduling.message,
                status = scheduling.status.name
            )
        }

        fun translate(schedulings: List<Scheduling>): List<SchedulingResponseDTO> {
            return schedulings.stream()
                .map { scheduling -> translate(scheduling) }
                .collect(Collectors.toList())
        }
    }
}