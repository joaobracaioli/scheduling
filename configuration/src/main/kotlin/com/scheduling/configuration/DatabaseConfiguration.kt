package com.scheduling.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class DatabaseConfiguration(
    private val dataSourceProperties: DataSourceProperties
) {

    @Bean
    fun dataSourceScheduling(): DataSource {
        val hikariConfig = getHikariConfig(dataSourceProperties)
        return HikariDataSource(hikariConfig)
    }

    @Bean
    fun jdbcTemplateSchedulingDataSource(dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }

    @Bean
    fun namedParameterJdbcTemplateScheduling(jdbcTemplate: JdbcTemplate): NamedParameterJdbcTemplate {
        return NamedParameterJdbcTemplate(jdbcTemplate)
    }

    private fun getHikariConfig(dataSourceProperties: DataSourceProperties): HikariConfig {
        val hikariConfig = HikariConfig()

        hikariConfig.jdbcUrl = dataSourceProperties.jdbcUrl
        hikariConfig.username = dataSourceProperties.username
        hikariConfig.password = dataSourceProperties.password

        return hikariConfig
    }
}
