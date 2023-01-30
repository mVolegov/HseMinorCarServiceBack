package com.mvoleg.hseminorcarserviceback.restcontroller

import com.mvoleg.hseminorcarserviceback.dto.CarRepairRequestDTO
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestArchiveEntity
import com.mvoleg.hseminorcarserviceback.entity.CarRepairRequestEntity
import com.mvoleg.hseminorcarserviceback.service.CarRepairRequestService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/requests")
@CrossOrigin("http://localhost:3000")
class CarRepairRequestRestController(
    val carRepairRequestService: CarRepairRequestService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<CarRepairRequestDTO>> {
        val allRequests = carRepairRequestService.getAll()
        return ResponseEntity(allRequests, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getRequestById(@PathVariable("id") id: Long): ResponseEntity<CarRepairRequestDTO> {
        val request = carRepairRequestService.getById(id)
        return ResponseEntity(request, HttpStatus.OK)
    }

    @PostMapping
    fun createRequest(@RequestBody carRepairRequestDTO: CarRepairRequestDTO): ResponseEntity<CarRepairRequestEntity> {
        val createdRequest = carRepairRequestService.create(carRepairRequestDTO)
        return ResponseEntity(createdRequest, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateRequest(@PathVariable("id") id: Long,
                      @RequestBody carRepairRequestDTO: CarRepairRequestDTO): ResponseEntity<CarRepairRequestEntity> {
        val updatedRequest = carRepairRequestService.update(id, carRepairRequestDTO)
        return ResponseEntity(updatedRequest, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<String> {
        carRepairRequestService.delete(id)
        return ResponseEntity("Car repair request with id $id was successfully deleted", HttpStatus.OK)
    }

    @PutMapping("/set-done/{id}")
    fun setRequestStatusDone(@PathVariable("id") id: Long): ResponseEntity<String> {
        carRepairRequestService.setStatusDone(id)
        return ResponseEntity("Car repair request with id $id was successfully done", HttpStatus.OK)
    }
}