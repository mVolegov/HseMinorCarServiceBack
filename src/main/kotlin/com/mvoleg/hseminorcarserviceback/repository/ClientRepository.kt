package com.mvoleg.hseminorcarserviceback.repository

import com.mvoleg.hseminorcarserviceback.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository: JpaRepository<ClientEntity, Long> {

    fun findByCarOwnerName(carOwnerName: String): ClientEntity?
}