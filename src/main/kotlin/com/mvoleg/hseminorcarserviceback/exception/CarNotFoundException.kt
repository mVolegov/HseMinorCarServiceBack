package com.mvoleg.hseminorcarserviceback.exception

import org.springframework.http.HttpStatus

class CarNotFoundException(carLicensePlateNumber: String): BaseApiException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "car.not.found",
        description = "Car with license plate number $carLicensePlateNumber not found"
    )
)