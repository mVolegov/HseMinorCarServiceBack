package com.mvoleg.hseminorcarserviceback.entity

import jakarta.persistence.*
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.math.BigDecimal

@Entity
@Table(name = "car_repair_request")
class CarRepairRequestEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "client_id")
    var client: ClientEntity,

    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "car_id")
    var car: CarEntity,

    @Column(name = "appeal_reason")
    var appealReason: String = "",

    @Column(name = "declared_works", nullable = false)
    var declaredWorks: String = "",

    @Column(name = "total_price_works", nullable = false)
    var totalPriceOfWorks: BigDecimal = BigDecimal.ZERO,

    @Column(name = "status", nullable = false)
    var status: String = ""
)