package com.example.cusCom.estimate.model.parts

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null

    @Column(nullable = false, unique = true)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val power:Int=power
    @Column(nullable = false)
    val efficiency:String=efficiency
    @Column(nullable = false)
    val modular:String=modular
    @Column(nullable = false)
    val length:Int=length
}
