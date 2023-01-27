package com.mvoleg.hseminorcarserviceback.exception

data class ApiError(
    val errorCode: String,
    val description: String
)