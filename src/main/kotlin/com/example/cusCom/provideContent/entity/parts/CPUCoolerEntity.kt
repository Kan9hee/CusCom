package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.CPUCooler
import jakarta.persistence.*

@Table(name="cpu_cooler")
@Entity
class CPUCoolerEntity(cpuCooler: CPUCooler){
    @Id
    @Column(nullable = false)
    var name:String=cpuCooler.name
    @Column(nullable = false)
    var manufacturer:String=cpuCooler.manufacturer
    @Column(nullable = false)
    var coolingType:String=cpuCooler.coolingType
    @Column(nullable = false)
    var coolerForm:String=cpuCooler.coolerForm
    @Column(nullable = false)
    var height:Int=cpuCooler.height
    @Column(nullable = false)
    var length:Int=cpuCooler.length
    @Column(nullable = false)
    var width:Int=cpuCooler.width
    @Column(nullable = false)
    var tdp:Int=cpuCooler.TDP

    fun update(cpuCooler: CPUCooler) {
        this.name=cpuCooler.name
        this.manufacturer=cpuCooler.manufacturer
        this.coolingType=cpuCooler.coolingType
        this.coolerForm=cpuCooler.coolerForm
        this.height=cpuCooler.height
        this.length=cpuCooler.length
        this.width=cpuCooler.width
        this.tdp=cpuCooler.TDP
    }
}
