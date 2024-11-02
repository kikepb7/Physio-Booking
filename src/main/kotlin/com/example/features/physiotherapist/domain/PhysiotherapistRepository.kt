package com.example.features.physiotherapist.domain

import com.example.features.physiotherapist.domain.model.PhysiotherapistModel

interface PhysiotherapistRepository {
    suspend fun registerPhysio(physiotherapistModel: PhysiotherapistModel): Int
    suspend fun findPhysios(): List<PhysiotherapistModel>?
    suspend fun findPhysioById(physioId: Int): PhysiotherapistModel?
    suspend fun updatePhysioById(physioId: Int, physiotherapistModel: PhysiotherapistModel): Boolean
    suspend fun deletePhysioById(physioId: Int): Boolean
}