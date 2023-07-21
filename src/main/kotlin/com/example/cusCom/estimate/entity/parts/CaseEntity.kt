package com.example.cusCom.estimate.dto.parts

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
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var caseType:String=caseType
    @Column(nullable = false)
    var maxMotherBoard:String=maxMotherBoard
    @Column(nullable = false)
    var maxCoolingFan:Int=maxCoolingFan
    @Column(nullable = false)
    var builtInCoolingFan:Int=builtInCoolingFan
    @Column(nullable = false)
    var height:Int=height
    @Column(nullable = false)
    var length:Int=length
    @Column(nullable = false)
    var width:Int=width
    @Column(nullable = false)
    var powerLength:Int=powerLength
    @Column(nullable = false)
    var cpuCoolerHeight:Int=cpuCoolerHeight
    @Column(nullable = false)
    var graphicsCardLength:Int=graphicsCardLength

    fun update(case: Case){
        this.name=case.name
        this.manufacturer=case.manufacturer
        this.caseType=case.caseType
        this.maxMotherBoard=case.maxMotherBoard
        this.maxCoolingFan=case.maxCoolingFan
        this.builtInCoolingFan=case.builtInCoolingFan
        this.height=case.height
        this.length=case.length
        this.width=case.width
        this.powerLength=case.powerLength
        this.cpuCoolerHeight=case.cpuCoolerHeight
        this.graphicsCardLength=case.graphicsCardLength
    }
}
