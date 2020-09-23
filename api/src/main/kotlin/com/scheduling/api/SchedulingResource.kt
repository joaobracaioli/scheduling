package com.scheduling.api

import com.scheduling.api.model.SchedulingDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/scheduling")
interface SchedulingResource {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody scheduling : SchedulingDTO)
}