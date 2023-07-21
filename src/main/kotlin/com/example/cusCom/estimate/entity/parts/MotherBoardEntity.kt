package com.example.cusCom.estimate.dto.parts

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
    @Column(nullable = false)
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var cpuType:String=cpuType
    @Column(nullable = false)
    var socket:String=socket
    @Column(nullable = false)
    var chipset:String=chipset
    @Column(nullable = false)
    var formFactor:String=formFactor
    @Column(nullable = false)
    var memoryType:String=memoryType
    @Column(nullable = false)
    var memorySlot:Int=memorySlot
    @Column(nullable = false)
    var ssdM2Slot:Int=ssdM2Slot
    @Column(nullable = false)
    var ssdSATASlot:Int=ssdSATASlot

    fun update(motherBoard: MotherBoard){
        this.name=motherBoard.name
        this.manufacturer=motherBoard.manufacturer
        this.cpuType=motherBoard.cpuType
        this.socket=motherBoard.socket
        this.chipset=motherBoard.chipset
        this.formFactor=motherBoard.formFactor
        this.memoryType=motherBoard.memoryType
        this.memorySlot=motherBoard.memorySlot
        this.ssdM2Slot=motherBoard.ssdM2Slot
        this.ssdSATASlot=motherBoard.ssdSATASlot
    }
}
