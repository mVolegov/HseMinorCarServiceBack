package com.mvoleg.hseminorcarserviceback.service.impl

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestStatus
import com.mvoleg.hseminorcarserviceback.exception.CarRepairRequestNotFoundException
import com.mvoleg.hseminorcarserviceback.mapper.Mapper
import com.mvoleg.hseminorcarserviceback.repository.CarRepairRequestArchiveRepository
import com.mvoleg.hseminorcarserviceback.repository.CarRepairRequestRepository
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarRepairRequestServiceImpl(
    val carRepairRequestRepository: CarRepairRequestRepository,
    val carRepairRequestArchiveRepository: CarRepairRequestArchiveRepository
): CarRepairRequestService {

    override fun getAll(): List<CarRepairRequestDTO> {
        return carRepairRequestRepository.findAll().map { Mapper.mapCarRepairRequestEntityToDTO(it) }
    }

    override fun getById(id: Long): CarRepairRequestDTO {
        val carRepairRequestEntity = (carRepairRequestRepository
            .findByIdOrNull(id)
            ?: throw CarRepairRequestNotFoundException(id))

        return Mapper.mapCarRepairRequestEntityToDTO(carRepairRequestEntity);
    }

    @Transactional
    override fun create(dto: CarRepairRequestDTO): CarRepairRequestEntity {
        val carRepairRequestEntityToSave = Mapper.mapCarRepairRequestDTOtoEntity(dto)

        return carRepairRequestRepository.save(carRepairRequestEntityToSave)
    }

    @Transactional
    override fun update(requestId: Long, dto: CarRepairRequestDTO): CarRepairRequestEntity {
        carRepairRequestRepository
            .findByIdOrNull(requestId)
            ?: throw CarRepairRequestNotFoundException(requestId)

        val carRepairRequestEntity = Mapper.mapCarRepairRequestDTOtoEntity(dto)
        carRepairRequestEntity.id = requestId

        return carRepairRequestRepository.save(carRepairRequestEntity)
    }

    override fun delete(id: Long) {
        if (!carRepairRequestRepository.existsById(id)) throw CarRepairRequestNotFoundException(id)

        carRepairRequestRepository.deleteById(id)
    }

    @Transactional
    override fun setStatusDone(id: Long): CarRepairRequestEntity {
        val carRepairRequestEntity = carRepairRequestRepository
            .findByIdOrNull(id)
            ?: throw CarRepairRequestNotFoundException(id)

        carRepairRequestEntity.status = CarRepairRequestStatus.DONE.name

        carRepairRequestArchiveRepository.save(
            Mapper.extractArchiveEntityFromCarRepairRequestEntity(carRepairRequestEntity)
        )

        return carRepairRequestRepository.save(carRepairRequestEntity)
    }

    override fun getArchive(): List<CarRepairRequestArchiveEntity> {
        return carRepairRequestArchiveRepository.findAll()
    }
}