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
                     motherBoard:String,
                     memory:String,
                     dataStorage:String,
                     graphicsCard:String,
                     cpuCooler:String,
                     powerSupply:String,
                     case:String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null
    @Column(nullable = false, unique = true)
    var userName:String=userName

    @Column(nullable = false)
    var motherBoard:String=motherBoard
    @Column(nullable = false)
    var memory:String=memory
    @Column(nullable = false)
    var dataStorage:String=dataStorage
    @Column(nullable = false)
    var graphicsCard:String=graphicsCard
    @Column(nullable = false)
    var cpuCooler:String=cpuCooler
    @Column(nullable = false)
    var powerSupply:String=powerSupply
    @Column(nullable = false)
    var desktopCase:String=case
}