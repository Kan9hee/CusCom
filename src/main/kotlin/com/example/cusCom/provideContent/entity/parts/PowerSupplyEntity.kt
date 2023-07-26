package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.PowerSupply
import jakarta.persistence.*

@Table(name="power_supply")
@Entity
class PowerSupplyEntity(powerSupply: PowerSupply){
    @Id
    @Column(nullable = false)
    var name:String=powerSupply.name
    @Column(nullable=false)
    var imageUrl:String=powerSupply.imageUrl

    @Column(nullable = false)
    var manufacturer:String=powerSupply.manufacturer
    @Column(nullable = false)
    var power:Int=powerSupply.power
    @Column(nullable = false)
    var efficiency:String=powerSupply.efficiency
    @Column(nullable = false)
    var modular:String=powerSupply.modular
    @Column(nullable = false)
    var length:Int=powerSupply.length

    fun update(powerSupply: PowerSupply){
        this.name=powerSupply.name
        this.imageUrl=powerSupply.imageUrl
        this.manufacturer=powerSupply.manufacturer
        this.power=powerSupply.power
        this.efficiency=powerSupply.efficiency
        this.modular=powerSupply.modular
        this.length=powerSupply.length
    }
}
