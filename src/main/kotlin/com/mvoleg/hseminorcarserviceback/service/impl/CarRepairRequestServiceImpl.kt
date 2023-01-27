package com.mvoleg.hseminorcarserviceback.service.impl

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity
import com.mvoleg.hseminorcarserviceback.exception.CarRepairRequestNotFoundException
import com.mvoleg.hseminorcarserviceback.mapper.Mapper
import com.mvoleg.hseminorcarserviceback.repository.CarRepairRequestRepository
import com.mvoleg.hseminorcarserviceback.repository.CarRepository
import com.mvoleg.hseminorcarserviceback.repository.ClientRepository
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestService
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CarRepairRequestServiceImpl(
    val carRepairRequestRepository: CarRepairRequestRepository,
    val clientRepository: ClientRepository,
    val carRepository: CarRepository
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
        val clientEntity = clientRepository
            .findByCarOwnerName(dto.carOwnerName.trim())
            ?: clientRepository.save(Mapper.extractClientEntityFromCarRepairRequestDTO(dto))

        val carEntity = carRepository
            .findByCarLicensePlateNumber(dto.carLicensePlateNumber.trim())
            ?: carRepository.save(Mapper.extractCarEntityFromCarRepairRequestDTO(dto))

        val carRepairRequestEntityToSave = CarRepairRequestEntity(
            0,
            clientEntity,
            carEntity,
            dto.appealReason,
            dto.declaredWorks,
            dto.totalPriceOfWorks,
            dto.status ?: ""
        )

        return carRepairRequestRepository.save(carRepairRequestEntityToSave)
    }

    @Transactional
    override fun update(requestId: Long, dto: CarRepairRequestDTO): CarRepairRequestEntity {
        val carRepairRequestEntity = carRepairRequestRepository
            .findByIdOrNull(requestId)
            ?: throw CarRepairRequestNotFoundException(requestId)

        carRepairRequestEntity.car = Mapper.extractCarEntityFromCarRepairRequestDTO(dto)
        carRepairRequestEntity.client = Mapper.extractClientEntityFromCarRepairRequestDTO(dto)
        carRepairRequestEntity.appealReason = dto.appealReason
        carRepairRequestEntity.declaredWorks = dto.declaredWorks
        carRepairRequestEntity.totalPriceOfWorks = dto.totalPriceOfWorks

        return carRepairRequestRepository.save(carRepairRequestEntity)
    }

    override fun delete(id: Long) {
        if (!carRepairRequestRepository.existsById(id)) throw CarRepairRequestNotFoundException(id)

        carRepairRequestRepository.deleteById(id)
    }

    override fun setStatusDone(id: Long): CarRepairRequestEntity {
        val carRepairRequestEntity = carRepairRequestRepository
            .findByIdOrNull(id)
            ?: throw CarRepairRequestNotFoundException(id)

        carRepairRequestEntity.status = "DONE"

        return carRepairRequestRepository.save(carRepairRequestEntity)
    }
}