package com.example.cusCom.provideContent.dto.parts

import jakarta.persistence.*

@Table(name="cpu_cooler")
@Entity
class CPUCoolerEntity(name:String,
                      manufacturer:String,
                      coolingType:String,
                      coolerForm:String,
                      height:Int,
                      length:Int,
                      width:Int,
                      TDP:Int){
    @Id
    @Column(nullable = false)
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var coolingType:String=coolingType
    @Column(nullable = false)
    var coolerForm:String=coolerForm
    @Column(nullable = false)
    var height:Int=height
    @Column(nullable = false)
    var length:Int=length
    @Column(nullable = false)
    var width:Int=width
    @Column(nullable = false)
    var tdp:Int=TDP

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
