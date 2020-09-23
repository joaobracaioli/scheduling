package com.scheduling.api.model

import com.fasterxml.jackson.annotation.JsonProperty

data class SchedulingDTO (

    @JsonProperty("beneficiary")
    val beneficiary: String = "",

    @JsonProperty("dateSend")
    val dateSend: String,

    @JsonProperty("hourSend")
    val hourSend: String,

    @JsonProperty("message")
    val message: String,

    @JsonProperty("typeSend")
    val typeSend: String
)