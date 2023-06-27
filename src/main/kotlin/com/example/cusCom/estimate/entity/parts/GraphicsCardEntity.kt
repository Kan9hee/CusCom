package com.example.cusCom.estimate.dto.parts

import jakarta.persistence.*

@Table(name="graphics_card")
@Entity
class GraphicsCardEntity(name:String,
                         manufacturer:String,
                         chipsetManufacturer:String,
                         gpuType:String,
                         length:Int,
                         basicPower:Int,
                         maxPower:Int,
                         phase:Int){
    @Id
    @Column(nullable = false)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val chipsetManufacturer:String=chipsetManufacturer
    @Column(nullable = false)
    val gpuType:String=gpuType
    @Column(nullable = false)
    val length:Int=length
    @Column(nullable = false)
    val basicPower:Int=basicPower
    @Column(nullable = false)
    val maxPower:Int=maxPower
    @Column(nullable = false)
    val phase:Int=phase
}
