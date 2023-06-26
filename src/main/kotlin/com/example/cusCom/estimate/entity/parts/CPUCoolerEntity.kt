package com.example.cusCom.estimate.model.parts

import jakarta.persistence.*

@Table(name="cpu_cooler")
@Entity
class CPUCoolerEntity(name:String,
                      manufacturer:String,
                      coolingType:String,
                      coolerForm:String,
                      height:Int,
                      length:Int,
                      width:Int){
    @Id
    @Column(nullable = false)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val coolingType:String=coolingType
    @Column(nullable = false)
    val coolerForm:String=coolerForm
    @Column(nullable = false)
    val height:Int=height
    @Column(nullable = false)
    val length:Int=length
    @Column(nullable = false)
    val width:Int=width
}
