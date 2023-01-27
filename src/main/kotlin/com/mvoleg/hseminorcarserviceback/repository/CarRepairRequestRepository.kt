package com.mvoleg.hseminorcarserviceback.repository

import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepairRequestRepository: JpaRepository<CarRepairRequestEntity, Long>