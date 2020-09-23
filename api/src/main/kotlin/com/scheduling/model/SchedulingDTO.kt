package com.scheduling.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SchedulingDTO (

    @JsonProperty("beneficiary")
    val beneficiary: String = ""
)