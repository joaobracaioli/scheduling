package com.scheduling.core.entities

import java.time.LocalDateTime

class Scheduling (
    val beneficiary: String = "",
    val dateTime: LocalDateTime,
    val message: String,
    val sendType: SendType
)