package com.mvoleg.hseminorcarserviceback.mapper

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.*

class Mapper {

    companion object {

        fun mapCarRepairRequestDTOtoEntity(dto: CarRepairRequestDTO): CarRepairRequestEntity {
            return CarRepairRequestEntity(
                id = dto.id ?: 0L,
                client = extractClientEntityFromCarRepairRequestDTO(dto),
                car = extractCarEntityFromCarRepairRequestDTO(dto),
                appealReason = dto.appealReason,
                declaredWorks = dto.declaredWorks,
                totalPriceOfWorks = dto.totalPriceOfWorks,
                status = dto.status ?: CarRepairRequestStatus.ACCEPTED.statusName
            )
        }

        fun mapCarRepairRequestEntityToDTO(entity: CarRepairRequestEntity): CarRepairRequestDTO {
            return CarRepairRequestDTO(
                id = entity.id,
                carOwnerId = entity.client.id,
                carOwnerName = entity.client.carOwnerName,
                carOwnerContactInfo = entity.client.carOwnerContactInfo,
                carId = entity.car.id,
                carName = entity.car.carName,
                carManufactureYear = entity.car.carManufactureYear,
                carMileage = entity.car.carMileage,
                carColor = entity.car.carColor,
                carLicensePlateNumber = entity.car.carLicensePlateNumber,
                appealReason = entity.appealReason,
                declaredWorks = entity.declaredWorks,
                totalPriceOfWorks = entity.totalPriceOfWorks,
                status = entity.status
            )
        }

        fun extractClientEntityFromCarRepairRequestDTO(dto: CarRepairRequestDTO): ClientEntity {
            return ClientEntity(
                id = dto.carOwnerId ?: 0,
                carOwnerName = dto.carOwnerName,
                carOwnerContactInfo = dto.carOwnerContactInfo
            )
        }

        fun extractCarEntityFromCarRepairRequestDTO(dto: CarRepairRequestDTO): CarEntity {
            return CarEntity(
                dto.carId ?: 0,
                dto.carName,
                dto.carManufactureYear,
                dto.carMileage,
                dto.carColor,
                dto.carLicensePlateNumber
            )
        }

        fun extractArchiveEntityFromCarRepairRequestEntity(entity: CarRepairRequestEntity):
                CarRepairRequestArchiveEntity {
            return CarRepairRequestArchiveEntity(
                0,
                entity.client.carOwnerName,
                entity.client.carOwnerContactInfo,
                entity.car.carName,
                entity.car.carManufactureYear,
                entity.car.carMileage,
                entity.car.carColor,
                entity.car.carLicensePlateNumber,
                entity.appealReason,
                entity.declaredWorks,
                entity.totalPriceOfWorks,
                entity.status
            )
        }
    }
}