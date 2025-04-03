package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.dto.parts.PowerSupplyDTO
import jakarta.persistence.*

@Entity
@Table(name = "power_supply")
class PowerSupplyEntity(
    name: String,
    imageUrl: String,
    manufacturer: String,
    power: Int,
    efficiency: String,
    modular: String,
    length: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null

    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(nullable = false)
    var imageUrl: String = imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer: String = manufacturer
        protected set

    @Column(nullable = false)
    var power: Int = power
        protected set

    @Column(nullable = false)
    var efficiency: String = efficiency
        protected set

    @Column(nullable = false)
    var modular: String = modular
        protected set

    @Column(nullable = false)
    var length: Int = length
        protected set

    fun update(powerSupplyDTO: PowerSupplyDTO) {
        this.name = powerSupplyDTO.name
        if(powerSupplyDTO.imageUrl!=null)
            this.imageUrl = powerSupplyDTO.imageUrl
        this.manufacturer = powerSupplyDTO.manufacturer
        this.power = powerSupplyDTO.power
        this.efficiency = powerSupplyDTO.efficiency
        this.modular = powerSupplyDTO.modular
        this.length = powerSupplyDTO.length
    }
}
