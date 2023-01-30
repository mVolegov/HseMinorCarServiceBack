package com.mvoleg.hseminorcarserviceback.service

import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity

interface CarRepairRequestArchiveService {

    fun getArchive(): List<CarRepairRequestArchiveEntity>

    fun getArchivedRequestById(id: Long): CarRepairRequestArchiveEntity
}