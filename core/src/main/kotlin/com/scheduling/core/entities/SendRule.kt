package com.scheduling.core.entities

interface SendRule {

    fun validate(scheduling: Scheduling) : Boolean
}