package com.mvoleg.hseminorcarserviceback.repository

import com.mvoleg.hseminorcarserviceback.entity.CarEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository: JpaRepository<CarEntity, Long> {

    fun findByCarLicensePlateNumber(carLicensePlateNumber: String): CarEntity?
}