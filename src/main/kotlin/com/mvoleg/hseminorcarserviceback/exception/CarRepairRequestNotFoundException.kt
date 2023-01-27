package com.mvoleg.hseminorcarserviceback.exception

import org.springframework.http.HttpStatus

class CarRepairRequestNotFoundException(carRepairRequestId: Long): BaseApiException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "car.repair.request.not.found",
        description = "Car repair request with id $carRepairRequestId not found"
    )
)