package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.MotherBoard
import jakarta.persistence.*

@Table(name="mother_board")
@Entity
class MotherBoardEntity(motherBoard: MotherBoard){
    @Id
    @Column(nullable = false)
    var name:String=motherBoard.name
    @Column(nullable = false)
    var manufacturer:String=motherBoard.manufacturer
    @Column(nullable = false)
    var cpuType:String=motherBoard.cpuType
    @Column(nullable = false)
    var socket:String=motherBoard.socket
    @Column(nullable = false)
    var chipset:String=motherBoard.chipset
    @Column(nullable = false)
    var formFactor:String=motherBoard.formFactor
    @Column(nullable = false)
    var memoryType:String=motherBoard.memoryType
    @Column(nullable = false)
    var memorySlot:Int=motherBoard.memorySlot
    @Column(nullable = false)
    var ssdM2Slot:Int=motherBoard.ssdM2Slot
    @Column(nullable = false)
    var ssdSATASlot:Int=motherBoard.ssdSATASlot

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
