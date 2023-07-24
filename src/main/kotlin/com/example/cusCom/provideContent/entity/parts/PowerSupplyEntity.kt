package com.example.cusCom.provideContent.dto.parts

import jakarta.persistence.*

@Table(name="power_supply")
@Entity
class PowerSupplyEntity(name:String,
                        manufacturer:String,
                        power:Int,
                        efficiency:String,
                        modular:String,
                        length:Int){
    @Id
    @Column(nullable = false)
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var power:Int=power
    @Column(nullable = false)
    var efficiency:String=efficiency
    @Column(nullable = false)
    var modular:String=modular
    @Column(nullable = false)
    var length:Int=length

    fun update(powerSupply: PowerSupply){
        this.name=powerSupply.name
        this.manufacturer=powerSupply.manufacturer
        this.power=powerSupply.power
        this.efficiency=powerSupply.efficiency
        this.modular=powerSupply.modular
        this.length=powerSupply.length
    }
}
