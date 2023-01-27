package com.mvoleg.hseminorcarserviceback.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "client")
class ClientEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "car_owner_name", nullable = false, unique = true)
    var carOwnerName: String = "",

    @Column(name = "car_owner_info")
    var carOwnerContactInfo: String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    var repairRequests: List<CarRepairRequestEntity> = emptyList()
)