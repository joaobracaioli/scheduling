package com.scheduling.api

import com.scheduling.api.model.SchedulingCountDTO
import com.scheduling.api.model.SchedulingRequestDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus

@RequestMapping("/scheduling")
interface SchedulingResource {


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun add(@RequestBody scheduling : SchedulingRequestDTO)

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list(@RequestParam(defaultValue = "100") limit: Int, @RequestParam(defaultValue = "0") offset: Int) : SchedulingCountDTO
}