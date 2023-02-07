package com.mvoleg.hseminorcarserviceback.service.impl

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.*
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
        val carRepairRequestEntity = carRepairRequestRepository
            .findByIdOrNull(id)
            ?: throw CarRepairRequestNotFoundException(id)

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

        val carRepairRequestEntityToSave = Mapper.mapCarRepairRequestDTOtoEntity(dto)
        carRepairRequestEntityToSave.client.id = clientEntity.id
        carRepairRequestEntityToSave.car.id = carEntity.id

        return carRepairRequestRepository.save(carRepairRequestEntityToSave)
    }

    @Transactional
    override fun update(requestId: Long, dto: CarRepairRequestDTO): CarRepairRequestEntity {
        if (!carRepairRequestRepository.existsById(requestId)) {
            throw CarRepairRequestNotFoundException(requestId)
        }

        val carRepairRequestEntity = Mapper.mapCarRepairRequestDTOtoEntity(dto)
        carRepairRequestEntity.id = requestId

        return carRepairRequestRepository.save(carRepairRequestEntity)
    }

    @Transactional
    override fun delete(id: Long) {
        if (!carRepairRequestRepository.existsById(id)) {
            throw CarRepairRequestNotFoundException(id)
        }

        carRepairRequestRepository.deleteById(id)
    }

    @Transactional
    override fun setStatusDone(id: Long): CarRepairRequestEntity {
        val carRepairRequestEntity = carRepairRequestRepository
            .findByIdOrNull(id)
            ?: throw CarRepairRequestNotFoundException(id)

        if (carRepairRequestEntity.status != CarRepairRequestStatus.DONE.statusName) {
            carRepairRequestEntity.status = CarRepairRequestStatus.DONE.statusName

            carRepairRequestArchiveRepository.save(
                Mapper.extractArchiveEntityFromCarRepairRequestEntity(carRepairRequestEntity)
            )
        }

        carRepairRequestRepository.deleteById(id);

        return carRepairRequestEntity;
    }
}