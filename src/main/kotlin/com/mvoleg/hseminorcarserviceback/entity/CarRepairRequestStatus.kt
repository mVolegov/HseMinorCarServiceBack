package com.mvoleg.hseminorcarserviceback.entity

enum class CarRepairRequestStatus(val statusName: String) {
    NONE("Нет статуса"),
    IN_PROGRESS("В работе"),
    DONE("Сделано")
}