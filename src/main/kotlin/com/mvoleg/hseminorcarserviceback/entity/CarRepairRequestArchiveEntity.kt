package com.mvoleg.hseminorcarserviceback.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "car_repair_request_archive")
class CarRepairRequestArchiveEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "client_name")
    val carOwnerName: String = "",

    @Column(name = "client_contact_info")
    val carOwnerContactInfo: String = "",

    @Column(name = "car_name")
    val carName: String = "",

    @Column(name = "car_manufacture_year")
    val carManufactureYear: String = "",

    @Column(name = "car_mileage")
    val carMileage: Int = 0,

    @Column(name = "car_color")
    val carColor: String = "",

    @Column(name = "car_license_plate_number")
    var carLicensePlateNumber: String = "",

    @Column(name = "appeal_reason")
    val appealReason: String = "",

    @Column(name = "declared_works")
    var declaredWorks: String = "",

    @Column(name = "total_price_works")
    var totalPriceOfWorks: BigDecimal = BigDecimal.ZERO,

    @Column(name = "status")
    var status: String = "",

    @Column(name = "created_date")
    var createdDate: LocalDateTime = LocalDateTime.MIN,

    @Column(name = "completed_date")
    var completedDate: LocalDateTime = LocalDateTime.MIN
)