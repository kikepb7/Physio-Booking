package com.example.data.entity.schedule

import com.example.data.entity.physiotherapist.Physiotherapist
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.time

private const val TABLE_NAME = "schedule"

object Schedule: IntIdTable(name = TABLE_NAME) {
    val physiotherapistId = reference(name = "physiotherapist_id", foreign = Physiotherapist).uniqueIndex().nullable()
    val dayOfWeek = varchar(name = "day_of_week", length = 50).uniqueIndex().nullable()
    val startTime = time(name = "start_time").uniqueIndex().nullable()
    val endTime = time(name = "end_time").uniqueIndex().nullable()
    val available = bool(name = "available").default(defaultValue = true).uniqueIndex().nullable()
}