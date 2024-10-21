package com.example.domain.repository.physiotherapist

import com.example.domain.model.PhysiotherapistModel

interface PhysiotherapistRepository {
    suspend fun registerPhysio(physiotherapistModel: PhysiotherapistModel): Int
    suspend fun findPhysios(): List<PhysiotherapistModel>?
    suspend fun findPhysioById(physioId: Int): PhysiotherapistModel?
    suspend fun updatePhysioById(physioId: Int, physiotherapistModel: PhysiotherapistModel): Boolean
    suspend fun deletePhysioById(physioId: Int): Boolean
}