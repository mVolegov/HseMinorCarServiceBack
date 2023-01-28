package com.mvoleg.hseminorcarserviceback.restcontroller

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("http://localhost:3000")
class CarRepairRequestRestController(
    val carRepairRequestService: CarRepairRequestService
) {

    @GetMapping("/requests")
    fun getAll(): ResponseEntity<List<CarRepairRequestDTO>> {
        val allRequests = carRepairRequestService.getAll()
        return ResponseEntity(allRequests, HttpStatus.OK)
    }

    @GetMapping("/request/{id}")
    fun getRequestById(@PathVariable("id") id: Long): ResponseEntity<CarRepairRequestDTO> {
        val request = carRepairRequestService.getById(id)
        return ResponseEntity(request, HttpStatus.OK)
    }

    @PostMapping("/request")
    fun createRequest(@RequestBody carRepairRequestDTO: CarRepairRequestDTO): ResponseEntity<CarRepairRequestEntity> {
        val createdRequest = carRepairRequestService.create(carRepairRequestDTO)
        return ResponseEntity(createdRequest, HttpStatus.CREATED)
    }

    @PutMapping("/request/{id}")
    fun updateRequest(@PathVariable("id") id: Long,
                      @RequestBody carRepairRequestDTO: CarRepairRequestDTO): ResponseEntity<CarRepairRequestEntity> {
        val updatedRequest = carRepairRequestService.update(id, carRepairRequestDTO)
        return ResponseEntity(updatedRequest, HttpStatus.OK)
    }

    @DeleteMapping("/request/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<String> {
        carRepairRequestService.delete(id)
        return ResponseEntity("Car repair request with id $id was successfully deleted", HttpStatus.OK)
    }

    @PutMapping("/request/set-done/{id}")
    fun setRequestStatusDone(@PathVariable("id") id: Long): ResponseEntity<String> {
        carRepairRequestService.setStatusDone(id)
        return ResponseEntity("Car repair request with id $id was successfully done", HttpStatus.OK)
    }

    @GetMapping("/requests/archive")
    fun getRequestsArchive(): ResponseEntity<List<CarRepairRequestArchiveEntity>> {
        val archive = carRepairRequestService.getArchive()
        return ResponseEntity(archive, HttpStatus.OK)
    }
}