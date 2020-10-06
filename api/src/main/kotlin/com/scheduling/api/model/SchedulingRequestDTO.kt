package com.scheduling.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.scheduling.api.validation.TypeSendValidator
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

data class SchedulingRequestDTO (

    @field:NotBlank
    @JsonProperty("beneficiary")
    val beneficiary: String = "",

    @JsonProperty("dateSend")
    val dateSend: String,

    @JsonProperty("hourSend")
    val hourSend: String,

    @field:NotEmpty
    @JsonProperty("message")
    val message: String,

    @field:TypeSendValidator
    @JsonProperty("typeSend")
    val typeSend: String
)