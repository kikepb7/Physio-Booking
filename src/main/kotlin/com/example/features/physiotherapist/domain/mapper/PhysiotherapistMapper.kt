package com.example.features.physiotherapist.domain.mapper

import com.example.features.physiotherapist.data.dao.Physiotherapist
import com.example.features.physiotherapist.domain.model.PhysiotherapistModel
import org.jetbrains.exposed.sql.ResultRow

fun physiotherapistToDomain(row: ResultRow): PhysiotherapistModel = PhysiotherapistModel(
    id = row[Physiotherapist.id].value,
    name = row[Physiotherapist.name],
    lastName = row[Physiotherapist.lastName],
    email = row[Physiotherapist.email],
    phone = row[Physiotherapist.phone],
    specialties = row[Physiotherapist.specialties],
    profileImage = row[Physiotherapist.profileImage]
)