package com.mvoleg.hseminorcarserviceback.restcontroller

import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/requests/archive")
class CarRepairRequestArchiveRestController(
    val carRepairRequestService: CarRepairRequestService
) {

    @GetMapping
    fun getRequestsArchive(): ResponseEntity<List<CarRepairRequestArchiveEntity>> {
        val archive = carRepairRequestService.getArchive()
        return ResponseEntity(archive, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getArchivedRequestById(@PathVariable("id") id: Long): ResponseEntity<CarRepairRequestArchiveEntity> {
        val archivedRequestById = carRepairRequestService.getArchievedRequestById(id)
        return ResponseEntity(archivedRequestById, HttpStatus.OK)
    }
}