package com.example.features.schedule.domain.mapper

import com.example.features.schedule.data.dao.Schedule
import com.example.features.schedule.domain.model.ScheduleModel
import org.jetbrains.exposed.sql.ResultRow

fun scheduleToDomain(row: ResultRow): ScheduleModel = ScheduleModel(
        id = row[Schedule.id].value,
        physiotherapistId = row[Schedule.physiotherapistId]?.value,
        dayOfWeek = row[Schedule.dayOfWeek],
        startTime = row[Schedule.startTime]?.hourOfDay.toString(),
        endTime = row[Schedule.endTime]?.hourOfDay.toString(),
        available = row[Schedule.available]
)