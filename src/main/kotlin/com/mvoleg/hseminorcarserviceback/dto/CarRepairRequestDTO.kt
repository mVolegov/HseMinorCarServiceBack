package com.mvoleg.hseminorcarserviceback.dto

import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestStatus
import java.math.BigDecimal

data class CarRepairRequestDTO(
    val id: Long? = null,
    val carOwnerId: Long? = null,
    val carOwnerName: String,
    val carOwnerContactInfo: String,
    val carId: Long? = null,
    val carName: String,
    val carManufactureYear: String,
    val carMileage: Int,
    val carColor: String,
    val carLicensePlateNumber: String,
    val appealReason: String,
    val declaredWorks: String,
    val totalPriceOfWorks: BigDecimal,
    val status: String? = CarRepairRequestStatus.ACCEPTED.statusName
)