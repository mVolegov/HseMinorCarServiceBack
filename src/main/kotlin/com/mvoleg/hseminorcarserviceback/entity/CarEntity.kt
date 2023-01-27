package com.mvoleg.hseminorcarserviceback.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "car")
class CarEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "car_name", nullable = false)
    var carName: String = "",

    @Column(name = "car_manufacture_year")
    var carManufactureYear: String = "",

    @Column(name = "car_mileage")
    var carMileage: Int = 0,

    @Column(name = "car_color")
    var carColor: String = "",

    @Column(name = "car_license_plate_number", unique = true)
    var carLicensePlateNumber: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var repairRequests: List<CarRepairRequestEntity> = emptyList()
)