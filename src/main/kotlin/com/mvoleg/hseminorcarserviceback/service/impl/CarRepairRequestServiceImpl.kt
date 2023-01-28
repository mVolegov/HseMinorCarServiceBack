package com.mvoleg.hseminorcarserviceback.service.impl

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestStatus
import com.mvoleg.hseminorcarserviceback.exception.CarRepairRequestNotFoundException
import com.mvoleg.hseminorcarserviceback.mapper.Mapper
import com.mvoleg.hseminorcarserviceback.repository.CarRepairRequestArchiveRepository
import com.mvoleg.hseminorcarserviceback.repository.CarRepairRequestRepository
import com.mvoleg.hseminorcarserviceback.repository.CarRepository
import com.mvoleg.hseminorcarserviceback.repository.ClientRepository
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CarRepairRequestServiceImpl(
    val carRepairRequestRepository: CarRepairRequestRepository,
    val clientRepository: ClientRepository,
    val carRepository: CarRepository,
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
        val clientEntity = clientRepository
            .findByCarOwnerName(dto.carOwnerName.trim())
            ?: clientRepository.save(Mapper.extractClientEntityFromCarRepairRequestDTO(dto))

        val carEntity = carRepository
            .findByCarLicensePlateNumber(dto.carLicensePlateNumber.trim())
            ?: carRepository.save(Mapper.extractCarEntityFromCarRepairRequestDTO(dto))

        if (carEntity.carColor != dto.carColor) {
            carEntity.carColor = dto.carColor

            if (carEntity.carMileage != dto.carMileage) {
                carEntity.carMileage = dto.carMileage
            }
        }

        val carRepairRequestEntityToSave = CarRepairRequestEntity(
            0,
            clientEntity,
            carEntity,
            dto.appealReason,
            dto.declaredWorks,
            dto.totalPriceOfWorks,
            dto.status
        )

        return carRepairRequestRepository.save(carRepairRequestEntityToSave)
    }

    @Transactional
    override fun update(requestId: Long, dto: CarRepairRequestDTO): CarRepairRequestEntity {
        val carRepairRequestEntity = carRepairRequestRepository
            .findByIdOrNull(requestId)
            ?: throw CarRepairRequestNotFoundException(requestId)

        carRepairRequestEntity.id = requestId
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