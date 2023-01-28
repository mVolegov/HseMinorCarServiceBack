package com.mvoleg.hseminorcarserviceback.entity

enum class CarRepairRequestStatus(val statusName: String) {
    NONE("Нет статуса"),
    ACCEPTED("Принято"),
    IN_PROGRESS("В работе"),
    DONE("Сделано")
}