package com.mvoleg.hseminorcarserviceback.mapper

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.CarEntity
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity
import com.mvoleg.hseminorcarserviceback.entity.ClientEntity
import org.hibernate.annotations.Comment

class Mapper {

    companion object {

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
                dto.carColor, dto.carLicensePlateNumber
            )
        }
    }
}