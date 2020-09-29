package com.scheduling.core.entities.role

import com.scheduling.core.entities.Scheduling
import com.scheduling.core.entities.SendRule
import com.scheduling.core.entities.SendType
import java.util.regex.Pattern

class Email: SendRule {

    private val regex = "^(.+)@(.+)$"

    override fun validate(scheduling: Scheduling): Boolean {
        if (SendType.EMAIL == scheduling.sendType) {
            return Pattern.compile(regex).matcher(scheduling.beneficiary).matches()
        }
        return true
    }
}