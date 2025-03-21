package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.dto.parts.MotherBoardDTO
import jakarta.persistence.*

@Entity
@Table(name = "mother_board")
class MotherBoardEntity(
    name: String,
    imageUrl: String,
    manufacturer: String,
    cpuType: String,
    socket: String,
    chipset: String,
    motherBoardFormFactor: String,
    memoryType: String,
    memorySlot: Int,
    ssdM2Slot: Int,
    ssdSATASlot: Int
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Long? = null

    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(nullable = false)
    var imageUrl: String = imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer: String = manufacturer
        protected set

    @Column(nullable = false)
    var cpuType: String = cpuType
        protected set

    @Column(nullable = false)
    var socket: String = socket
        protected set

    @Column(nullable = false)
    var chipset: String = chipset
        protected set

    @Column(nullable = false)
    var motherBoardFormFactor: String = motherBoardFormFactor
        protected set

    @Column(nullable = false)
    var memoryType: String = memoryType
        protected set

    @Column(nullable = false)
    var memorySlot: Int = memorySlot
        protected set

    @Column(nullable = false)
    var ssdM2Slot: Int = ssdM2Slot
        protected set

    @Column(nullable = false)
    var ssdSATASlot: Int = ssdSATASlot
        protected set

    fun update(motherBoardDTO: MotherBoardDTO) {
        this.name = motherBoardDTO.name
        this.imageUrl = motherBoardDTO.imageUrl
        this.manufacturer = motherBoardDTO.manufacturer
        this.cpuType = motherBoardDTO.cpuType
        this.socket = motherBoardDTO.socket
        this.chipset = motherBoardDTO.chipset
        this.motherBoardFormFactor = motherBoardDTO.motherBoardFormFactor
        this.memoryType = motherBoardDTO.memoryType
        this.memorySlot = motherBoardDTO.memorySlot
        this.ssdM2Slot = motherBoardDTO.ssdM2Slot
        this.ssdSATASlot = motherBoardDTO.ssdSATASlot
    }
}
