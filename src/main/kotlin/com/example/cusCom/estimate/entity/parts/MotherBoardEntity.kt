package com.example.cusCom.estimate.model.parts

import jakarta.persistence.*

@Table(name="mother_board")
@Entity
class MotherBoardEntity(name:String,
                        manufacturer:String,
                        cpuType:String,
                        socket:String,
                        chipset:String,
                        formFactor:String,
                        memoryType:String,
                        memorySlot:Int,
                        ssdM2Slot:Int,
                        ssdSATASlot:Int){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null

    @Column(nullable = false, unique = true)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val cpuType:String=cpuType
    @Column(nullable = false)
    val socket:String=socket
    @Column(nullable = false)
    val chipset:String=chipset
    @Column(nullable = false)
    val formFactor:String=formFactor
    @Column(nullable = false)
    val memoryType:String=memoryType
    @Column(nullable = false)
    val memorySlot:Int=memorySlot
    @Column(nullable = false)
    val ssdM2Slot:Int=ssdM2Slot
    @Column(nullable = false)
    val ssdSATASlot:Int=ssdSATASlot
}
