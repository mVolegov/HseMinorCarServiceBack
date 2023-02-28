package com.mvoleg.hseminorcarserviceback.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "car_repair_request")
class CarRepairRequestEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "client_id")
    var client: ClientEntity,

    @ManyToOne(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "car_id")
    var car: CarEntity,

    @Column(name = "appeal_reason")
    var appealReason: String = "",

    @Column(name = "declared_works", nullable = false)
    var declaredWorks: String = "",

    @Column(name = "total_price_works", nullable = false)
    var totalPriceOfWorks: BigDecimal = BigDecimal.ZERO,

    @Column(name = "status", nullable = false)
    var status: String = "",

    @Column(name = "created_date")
    var createdDate: LocalDateTime = LocalDateTime.MIN
)