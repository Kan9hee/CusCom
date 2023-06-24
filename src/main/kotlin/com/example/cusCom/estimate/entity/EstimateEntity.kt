package com.example.cusCom.estimate.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name="estimates")
@Entity
class EstimateEntity(userName:String,
                     cpu:String,
                     motherBoard:String,
                     memory:String,
                     dataStorage:String,
                     graphicsCard:String,
                     cpuCooler:String,
                     powerSupply:String,
                     desktopCase:String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
    @Column(nullable = false, unique = true)
    val userName:String=userName

    @Column(nullable = false)
    val cpu:String=cpu
    @Column(nullable = false)
    val motherBoard:String=motherBoard
    @Column(nullable = false)
    val memory:String=memory
    @Column(nullable = false)
    val dataStorage:String=dataStorage
    @Column(nullable = false)
    val graphicsCard:String=graphicsCard
    @Column(nullable = false)
    val cpuCooler:String=cpuCooler
    @Column(nullable = false)
    val powerSupply:String=powerSupply
    @Column(nullable = false)
    val desktopCase:String=desktopCase
}