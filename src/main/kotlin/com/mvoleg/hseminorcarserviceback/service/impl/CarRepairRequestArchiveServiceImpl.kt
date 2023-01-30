package com.mvoleg.hseminorcarserviceback.service.impl

import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity
import com.mvoleg.hseminorcarserviceback.repository.CarRepairRequestArchiveRepository
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestArchiveService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CarRepairRequestArchiveServiceImpl(
    val carRepairRequestArchiveRepository: CarRepairRequestArchiveRepository
): CarRepairRequestArchiveService {

    override fun getArchive(): List<CarRepairRequestArchiveEntity> {
        return carRepairRequestArchiveRepository.findAll()
    }

    override fun getArchivedRequestById(id: Long): CarRepairRequestArchiveEntity {
        return carRepairRequestArchiveRepository.findByIdOrNull(id) ?: throw RuntimeException("Not found")
    }
}