package com.mvoleg.hseminorcarserviceback.service

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity

interface CarRepairRequestService {

    fun getAll(): List<CarRepairRequestDTO>

    fun getById(id: Long): CarRepairRequestDTO

    fun create(dto: CarRepairRequestDTO): CarRepairRequestEntity

    fun update(id: Long, dto: CarRepairRequestDTO): CarRepairRequestEntity

    fun delete(id: Long)

    fun setStatusDone(id: Long): CarRepairRequestEntity
}