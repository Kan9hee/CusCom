package com.example.cusCom.estimate.model.parts

import jakarta.persistence.*

@Table(name="desktop_case")
@Entity
class CaseEntity(name:String,
                 manufacturer:String,
                 caseType:String,
                 maxMotherBoard:String,
                 maxCoolingFan:Int,
                 builtInCoolingFan:Int,
                 height:Int,
                 length:Int,
                 width:Int,
                 powerLength:Int,
                 cpuCoolerHeight:Int,
                 graphicsCardLength:Int){
    @Id
    @Column(nullable = false)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val caseType:String=caseType
    @Column(nullable = false)
    val maxMotherBoard:String=maxMotherBoard
    @Column(nullable = false)
    val maxCoolingFan:Int=maxCoolingFan
    @Column(nullable = false)
    val builtInCoolingFan:Int=builtInCoolingFan
    @Column(nullable = false)
    val height:Int=height
    @Column(nullable = false)
    val length:Int=length
    @Column(nullable = false)
    val width:Int=width
    @Column(nullable = false)
    val powerLength:Int=powerLength
    @Column(nullable = false)
    val cpuCoolerHeight:Int=cpuCoolerHeight
    @Column(nullable = false)
    val graphicsCardLength:Int=graphicsCardLength
}
