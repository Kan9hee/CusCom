package com.example.cusCom.provideContent.entity.mySQL.parts

import com.example.cusCom.provideContent.dto.parts.Case
import com.example.cusCom.provideContent.entity.mySQL.MotherBoardFormFactorEntity
import jakarta.persistence.*

@Table(name="\${dbString.mysql.table.case}")
@Entity
class CaseEntity(case: Case){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id:Long=0

    @Column(nullable = false)
    var name:String=case.name
        protected set

    @Column(nullable = false)
    var manufacturer:String=case.manufacturer
        protected set

    @Column(nullable = false)
    var caseType:String=case.caseType
        protected set

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="motherboard_formfactor_name",nullable = false)
    var motherBoardFormFactor: MotherBoardFormFactorEntity =
        MotherBoardFormFactorEntity(case.motherBoardFormFactor.name,case.motherBoardFormFactor.length,case.motherBoardFormFactor.width)
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
        this.motherBoardFormFactor=MotherBoardFormFactorEntity(case.motherBoardFormFactor.name,case.motherBoardFormFactor.length,case.motherBoardFormFactor.width)
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
