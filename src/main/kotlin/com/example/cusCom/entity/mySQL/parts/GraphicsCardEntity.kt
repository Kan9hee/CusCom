package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.dto.parts.GraphicsCardDTO
import jakarta.persistence.*

@Entity
@Table(name = "graphics_card")
class GraphicsCardEntity(
    name: String,
    imageUrl: String,
    manufacturer: String,
    chipsetManufacturer: String,
    gpuType: String,
    length: Int,
    basicPower: Int,
    maxPower: Int,
    phase: Int
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
    var chipsetManufacturer: String = chipsetManufacturer
        protected set

    @Column(nullable = false)
    var gpuType: String = gpuType
        protected set

    @Column(nullable = false)
    var length: Int = length
        protected set

    @Column(nullable = false)
    var basicPower: Int = basicPower
        protected set

    @Column(nullable = false)
    var maxPower: Int = maxPower
        protected set

    @Column(nullable = false)
    var phase: Int = phase
        protected set

    fun update(graphicsCardDTO: GraphicsCardDTO) {
        this.name = graphicsCardDTO.name
        if(graphicsCardDTO.imageUrl!=null)
            this.imageUrl = graphicsCardDTO.imageUrl
        this.manufacturer = graphicsCardDTO.manufacturer
        this.chipsetManufacturer = graphicsCardDTO.chipsetManufacturer
        this.gpuType = graphicsCardDTO.gpuType
        this.length = graphicsCardDTO.length
        this.basicPower = graphicsCardDTO.basicPower
        this.maxPower = graphicsCardDTO.maxPower
        this.phase = graphicsCardDTO.phase
    }
}
