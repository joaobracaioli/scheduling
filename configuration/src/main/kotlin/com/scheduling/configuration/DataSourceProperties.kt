package com.scheduling.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("database")
data class DataSourceProperties(
    val jdbcUrl: String,
    val username: String,
    val password: String,
    val autoCommit: Boolean,
    val registerMBeans: Boolean,
    val maximumPoolSize: Int,
    val validationQuery: String,
    val driverClassName: String,
    val maxLifeTimeMs: Long,
    val idleTimeoutMs: Long,
    val connectionTimeoutMs: Long,
    val leakDetectionThreshold: Long
)
