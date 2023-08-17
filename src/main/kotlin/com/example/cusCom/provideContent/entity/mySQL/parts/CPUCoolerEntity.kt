package com.example.cusCom.provideContent.entity.mySQL.parts

import com.example.cusCom.provideContent.dto.parts.CPUCooler
import jakarta.persistence.*

@Table(name="cpu_cooler")
@Entity
class CPUCoolerEntity(cpuCooler: CPUCooler){

    @Id
    @Column(nullable = false)
    var name:String=cpuCooler.name
        protected set

    @Column(nullable=false)
    var imageUrl:String=cpuCooler.imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer:String=cpuCooler.manufacturer
        protected set

    @Column(nullable = false)
    var coolingType:String=cpuCooler.coolingType
        protected set

    @Column(nullable = false)
    var coolerForm:String=cpuCooler.coolerForm
        protected set

    @Column(nullable = false)
    var height:Int=cpuCooler.height
        protected set

    @Column(nullable = false)
    var length:Int=cpuCooler.length
        protected set

    @Column(nullable = false)
    var width:Int=cpuCooler.width
        protected set

    @Column(nullable = false)
    var tdp:Int=cpuCooler.TDP
        protected set

    fun update(cpuCooler: CPUCooler) {
        this.name=cpuCooler.name
        this.imageUrl=cpuCooler.imageUrl
        this.manufacturer=cpuCooler.manufacturer
        this.coolingType=cpuCooler.coolingType
        this.coolerForm=cpuCooler.coolerForm
        this.height=cpuCooler.height
        this.length=cpuCooler.length
        this.width=cpuCooler.width
        this.tdp=cpuCooler.TDP
    }
}
