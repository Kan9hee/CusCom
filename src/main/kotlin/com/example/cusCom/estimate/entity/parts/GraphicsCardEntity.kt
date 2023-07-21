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
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var chipsetManufacturer:String=chipsetManufacturer
    @Column(nullable = false)
    var gpuType:String=gpuType
    @Column(nullable = false)
    var length:Int=length
    @Column(nullable = false)
    var basicPower:Int=basicPower
    @Column(nullable = false)
    var maxPower:Int=maxPower
    @Column(nullable = false)
    var phase:Int=phase

    fun update(graphicsCard: GraphicsCard){
        this.name=graphicsCard.name
        this.manufacturer=graphicsCard.manufacturer
        this.chipsetManufacturer=graphicsCard.chipsetManufacturer
        this.gpuType=graphicsCard.gpuType
        this.length=graphicsCard.length
        this.maxPower=graphicsCard.maxPower
        this.phase=graphicsCard.phase
    }
}
