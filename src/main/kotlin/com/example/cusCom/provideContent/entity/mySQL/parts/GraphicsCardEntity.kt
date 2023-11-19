package com.example.cusCom.provideContent.entity.mySQL.parts

import com.example.cusCom.provideContent.dto.parts.GraphicsCard
import jakarta.persistence.*

@Table(name="\${dbString.mysql.table.graphicsCard}")
@Entity
class GraphicsCardEntity(graphicsCard: GraphicsCard){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id:Long=0

    @Column(nullable = false)
    var name:String=graphicsCard.name
        protected set

    @Column(nullable=false)
    var imageUrl:String=graphicsCard.imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer:String=graphicsCard.manufacturer
        protected set

    @Column(nullable = false)
    var chipsetManufacturer:String=graphicsCard.chipsetManufacturer
        protected set

    @Column(nullable = false)
    var gpuType:String=graphicsCard.gpuType
        protected set

    @Column(nullable = false)
    var length:Int=graphicsCard.length
        protected set

    @Column(nullable = false)
    var basicPower:Int=graphicsCard.basicPower
        protected set

    @Column(nullable = false)
    var maxPower:Int=graphicsCard.maxPower
        protected set

    @Column(nullable = false)
    var phase:Int=graphicsCard.phase
        protected set

    fun update(graphicsCard: GraphicsCard){
        this.name=graphicsCard.name
        this.imageUrl=graphicsCard.imageUrl
        this.manufacturer=graphicsCard.manufacturer
        this.chipsetManufacturer=graphicsCard.chipsetManufacturer
        this.gpuType=graphicsCard.gpuType
        this.length=graphicsCard.length
        this.maxPower=graphicsCard.maxPower
        this.phase=graphicsCard.phase
    }
}
