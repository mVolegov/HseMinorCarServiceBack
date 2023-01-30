package com.mvoleg.hseminorcarserviceback.restcontroller

import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestArchiveService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/requests/archive")
@CrossOrigin("http://localhost:3000")
class CarRepairRequestArchiveRestController(
    val carRepairRequestArchiveService: CarRepairRequestArchiveService
) {

    @GetMapping
    fun getRequestsArchive(): ResponseEntity<List<CarRepairRequestArchiveEntity>> {
        val archive = carRepairRequestArchiveService.getArchive()
        return ResponseEntity(archive, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getArchivedRequestById(@PathVariable("id") id: Long): ResponseEntity<CarRepairRequestArchiveEntity> {
        val archivedRequestById = carRepairRequestArchiveService.getArchivedRequestById(id)
        return ResponseEntity(archivedRequestById, HttpStatus.OK)
    }
}