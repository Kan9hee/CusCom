package com.example.cusCom.provideContent.entity.mySQL.parts

import com.example.cusCom.provideContent.dto.parts.MotherBoard
import com.example.cusCom.provideContent.entity.mySQL.MotherBoardFormFactorEntity
import jakarta.persistence.*

@Table(name="mother_board")
@Entity
class MotherBoardEntity(motherBoard: MotherBoard){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id:Long=0

    @Column(nullable = false)
    var name:String=motherBoard.name
        protected set

    @Column(nullable=false)
    var imageUrl:String=motherBoard.imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer:String=motherBoard.manufacturer
        protected set

    @Column(nullable = false)
    var cpuType:String=motherBoard.cpuType
        protected set

    @Column(nullable = false)
    var socket:String=motherBoard.socket
        protected set

    @Column(nullable = false)
    var chipset:String=motherBoard.chipset
        protected set

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="motherboard_formfactor_name",nullable = false)
    var motherBoardFormFactor: MotherBoardFormFactorEntity =
        MotherBoardFormFactorEntity(motherBoard.motherBoardFormFactor.name,motherBoard.motherBoardFormFactor.length,motherBoard.motherBoardFormFactor.width)
        protected set

    @Column(nullable = false)
    var memoryType:String=motherBoard.memoryType
        protected set

    @Column(nullable = false)
    var memorySlot:Int=motherBoard.memorySlot
        protected set

    @Column(nullable = false)
    var ssdM2Slot:Int=motherBoard.ssdM2Slot
        protected set

    @Column(nullable = false)
    var ssdSATASlot:Int=motherBoard.ssdSATASlot
        protected set

    fun update(motherBoard: MotherBoard){
        this.name=motherBoard.name
        this.imageUrl=motherBoard.imageUrl
        this.manufacturer=motherBoard.manufacturer
        this.cpuType=motherBoard.cpuType
        this.socket=motherBoard.socket
        this.chipset=motherBoard.chipset
        this.motherBoardFormFactor=
            MotherBoardFormFactorEntity(motherBoard.motherBoardFormFactor.name,motherBoard.motherBoardFormFactor.length,motherBoard.motherBoardFormFactor.width)
        this.memoryType=motherBoard.memoryType
        this.memorySlot=motherBoard.memorySlot
        this.ssdM2Slot=motherBoard.ssdM2Slot
        this.ssdSATASlot=motherBoard.ssdSATASlot
    }
}
