package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.GraphicsCard
import jakarta.persistence.*

@Table(name="graphics_card")
@Entity
class GraphicsCardEntity(graphicsCard: GraphicsCard){
    @Id
    @Column(nullable = false)
    var name:String=graphicsCard.name
    @Column(nullable = false)
    var manufacturer:String=graphicsCard.manufacturer
    @Column(nullable = false)
    var chipsetManufacturer:String=graphicsCard.chipsetManufacturer
    @Column(nullable = false)
    var gpuType:String=graphicsCard.gpuType
    @Column(nullable = false)
    var length:Int=graphicsCard.length
    @Column(nullable = false)
    var basicPower:Int=graphicsCard.basicPower
    @Column(nullable = false)
    var maxPower:Int=graphicsCard.maxPower
    @Column(nullable = false)
    var phase:Int=graphicsCard.phase

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
