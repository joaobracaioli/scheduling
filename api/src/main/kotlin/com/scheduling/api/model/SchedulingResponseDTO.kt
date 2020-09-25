package com.scheduling.api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SchedulingResponseDTO (

    @JsonProperty("beneficiary")
    val beneficiary: String,

    @JsonProperty("despatch")
    val despatch: String,

    @JsonProperty("message")
    val message: String,

    @JsonProperty("status")
    val status: String

)