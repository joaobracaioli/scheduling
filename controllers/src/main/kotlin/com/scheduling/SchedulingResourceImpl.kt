package com.scheduling

import com.scheduling.model.SchedulingDTO
import org.springframework.web.bind.annotation.RestController

@RestController
class SchedulingResourceImpl : SchedulingResource {
    override fun add(scheduling: SchedulingDTO): String {
        return "ok"
    }
}