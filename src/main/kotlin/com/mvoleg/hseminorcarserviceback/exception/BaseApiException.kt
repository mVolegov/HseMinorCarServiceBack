package com.mvoleg.hseminorcarserviceback.exception

import org.springframework.http.HttpStatus

abstract class BaseApiException(
    val httpStatus: HttpStatus,
    val apiError: ApiError
): RuntimeException(apiError.description)