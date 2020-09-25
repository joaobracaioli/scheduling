package com.scheduling.gateway.database.mapper

import com.scheduling.core.entities.Scheduling
import com.scheduling.core.entities.SendType
import com.scheduling.core.entities.Status
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet
import java.util.UUID

class SchedulingMapper : RowMapper<Scheduling> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Scheduling? {

        return Scheduling(
            id =  UUID.fromString(rs.getString("id")),
            beneficiary = rs.getString("beneficiary"),
            despatch = rs.getTimestamp("despatch").toLocalDateTime(),
            message =  rs.getString("message"),
            sendType =  SendType.valueOf(rs.getString("type_send")),
            status = Status.valueOf(rs.getString("status"))
        )
    }
}