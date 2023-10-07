package com.example.cusCom.provideContent.entity.mySQL.parts

import com.example.cusCom.provideContent.dto.parts.PowerSupply
import jakarta.persistence.*

@Table(name="power_supply")
@Entity
class PowerSupplyEntity(powerSupply: PowerSupply){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id:Long=0

    @Column(nullable = false)
    var name:String=powerSupply.name
        protected set

    @Column(nullable=false)
    var imageUrl:String=powerSupply.imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer:String=powerSupply.manufacturer
        protected set

    @Column(nullable = false)
    var power:Int=powerSupply.power
        protected set

    @Column(nullable = false)
    var efficiency:String=powerSupply.efficiency
        protected set

    @Column(nullable = false)
    var modular:String=powerSupply.modular
        protected set

    @Column(nullable = false)
    var length:Int=powerSupply.length
        protected set

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
