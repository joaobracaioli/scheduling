package com.scheduling.core.entities

import java.time.LocalDateTime
import java.util.UUID

class Scheduling (
    val id: UUID,
    val beneficiary: String = "",
    val despatch: LocalDateTime,
    val message: String,
    val sendType: SendType,
    val status: Status
)