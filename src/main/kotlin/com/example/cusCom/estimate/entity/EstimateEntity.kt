package com.example.cusCom.estimate.entity

import com.example.cusCom.estimate.dto.parts.*
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="estimates")
@Entity
class EstimateEntity(userName:String,
                     cpu: CPU,
                     motherBoard:MotherBoard,
                     memory:Memory,
                     dataStorage:DataStorage,
                     graphicsCard:GraphicsCard,
                     cpuCooler:CPUCooler,
                     powerSupply:PowerSupply,
                     desktopCase:Case) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
    @Column(nullable = false, unique = true)
    val userName:String=userName

    @Column(nullable = false)
    val cpu:String=cpu.name
    @Column(nullable = false)
    val motherBoard:String=motherBoard.name
    @Column(nullable = false)
    val memory:String=memory.name
    @Column(nullable = false)
    val dataStorage:String=dataStorage.name
    @Column(nullable = false)
    val graphicsCard:String=graphicsCard.name
    @Column(nullable = false)
    val cpuCooler:String=cpuCooler.name
    @Column(nullable = false)
    val powerSupply:String=powerSupply.name
    @Column(nullable = false)
    val desktopCase:String=desktopCase.name
}