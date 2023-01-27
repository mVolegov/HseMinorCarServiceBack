package com.mvoleg.hseminorcarserviceback.exception

import org.springframework.http.HttpStatus

class ClientNotFoundException(carOwnerName: String): BaseApiException(
    HttpStatus.NOT_FOUND,
    ApiError(
        errorCode = "client.not.found",
        description = "Client with name $carOwnerName not found"
    )
)