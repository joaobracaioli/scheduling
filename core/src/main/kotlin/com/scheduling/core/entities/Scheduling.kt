package com.scheduling.core.entities

import java.time.LocalDateTime

class Scheduling (
    val beneficiary: String = "",
    val despatch: LocalDateTime,
    val message: String,
    val sendType: SendType
)