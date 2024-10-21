package com.example.data.repository.physiotherapist

import com.example.data.entity.physiotherapist.Physiotherapist
import com.example.domain.model.PhysiotherapistModel
import com.example.domain.repository.physiotherapist.PhysiotherapistRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class PhysiotherapistRepositoryImpl: PhysiotherapistRepository {
    override suspend fun registerPhysio(physiotherapistModel: PhysiotherapistModel): Int {
        return transaction {
            Physiotherapist.insertAndGetId {
                it[name] = physiotherapistModel.name
                it[lastName] = physiotherapistModel.lastName
                it[email] = physiotherapistModel.email
                it[phone] = physiotherapistModel.phone
                it[specialties] = physiotherapistModel.specialties
                it[profileImage] = physiotherapistModel.profileImage
            }.value
        }
    }

    override suspend fun findPhysios(): List<PhysiotherapistModel>? {
        return transaction {
            Physiotherapist.selectAll().map {
                PhysiotherapistModel(
                    name = it[Physiotherapist.name],
                    lastName = it[Physiotherapist.lastName],
                    email = it[Physiotherapist.email],
                    phone = it[Physiotherapist.phone],
                    specialties = it[Physiotherapist.specialties],
                    profileImage = it[Physiotherapist.profileImage]
                )
            }
        }
    }

    override suspend fun findPhysioById(physioId: Int): PhysiotherapistModel? {
        return transaction {
            Physiotherapist.selectAll().where { Physiotherapist.id eq physioId }
                .map {
                    PhysiotherapistModel(
                        name = it[Physiotherapist.name],
                        lastName = it[Physiotherapist.lastName],
                        email = it[Physiotherapist.email],
                        phone = it[Physiotherapist.phone],
                        specialties = it[Physiotherapist.specialties],
                        profileImage = it[Physiotherapist.profileImage]
                    )
                }
                .singleOrNull()
        }
    }

    override suspend fun updatePhysioById(physioId: Int, physiotherapistModel: PhysiotherapistModel): Boolean {
        return transaction {
            val updatePhysios = Physiotherapist.update({ Physiotherapist.id eq physioId }) {
                it[name] = physiotherapistModel.name
                it[lastName] = physiotherapistModel.lastName
                it[email] = physiotherapistModel.email
                it[phone] = physiotherapistModel.phone
                it[specialties] = physiotherapistModel.specialties
                it[profileImage] = physiotherapistModel.profileImage
            }
            updatePhysios > 0
        }
    }

    override suspend fun deletePhysioById(physioId: Int): Boolean {
        return transaction {
            val deletePhysios = Physiotherapist.deleteWhere { Physiotherapist.id eq physioId }

            deletePhysios > 0
        }
    }
}