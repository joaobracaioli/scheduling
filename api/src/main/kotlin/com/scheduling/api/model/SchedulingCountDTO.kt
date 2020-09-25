package com.scheduling.api.model

data class SchedulingCountDTO (
    val schedulings : List<SchedulingResponseDTO>,
    val total: Long?
)