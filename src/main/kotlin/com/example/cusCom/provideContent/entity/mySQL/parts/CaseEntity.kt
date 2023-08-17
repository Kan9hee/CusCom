package com.example.cusCom.provideContent.entity.mySQL.parts

import com.example.cusCom.provideContent.dto.parts.Case
import jakarta.persistence.*

@Table(name="desktop_case")
@Entity
class CaseEntity(case: Case){

    @Id
    @Column(nullable = false)
    var name:String=case.name
        protected set

    @Column(nullable = false)
    var manufacturer:String=case.manufacturer
        protected set

    @Column(nullable = false)
    var caseType:String=case.caseType
        protected set

    @Column(nullable = false)
    var maxMotherBoard:String=case.maxMotherBoard
        protected set

    @Column(nullable = false)
    var maxCoolingFan:Int=case.maxCoolingFan
        protected set

    @Column(nullable = false)
    var builtInCoolingFan:Int=case.builtInCoolingFan
        protected set

    @Column(nullable = false)
    var height:Int=case.height
        protected set

    @Column(nullable = false)
    var length:Int=case.length
        protected set

    @Column(nullable = false)
    var width:Int=case.width
        protected set

    @Column(nullable = false)
    var powerLength:Int=case.powerLength
        protected set

    @Column(nullable = false)
    var cpuCoolerHeight:Int=case.cpuCoolerHeight
        protected set

    @Column(nullable = false)
    var graphicsCardLength:Int=case.graphicsCardLength
        protected set

    @Column(nullable=false)
    var imageUrl:String=case.imageUrl
        protected set

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
        this.imageUrl=case.imageUrl
    }
}
