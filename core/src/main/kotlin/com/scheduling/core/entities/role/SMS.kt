package com.scheduling.core.entities.role

import com.scheduling.core.entities.Scheduling
import com.scheduling.core.entities.SendRule
import com.scheduling.core.entities.SendType
import java.util.regex.Pattern

class SMS: SendRule {

    private val regex = "^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}\$" // (xx) xxxxx-xxxx

    override fun validate(scheduling: Scheduling): Boolean {
        if (SendType.SMS == scheduling.sendType) {
            return Pattern.compile(regex).matcher(scheduling.beneficiary).matches()
        }
        return true
    }
}