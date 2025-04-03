package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.config.InnerStringsConfig
import com.example.cusCom.dto.parts.CaseDTO
import jakarta.persistence.*

@Entity
@Table(name = "desktop_case")
class CaseEntity(
    name: String,
    manufacturer: String,
    caseType: String,
    motherBoardFormFactor: String,
    maxCoolingFan: Int,
    builtInCoolingFan: Int,
    height: Int,
    length: Int,
    width: Int,
    powerLength: Int,
    cpuCoolerHeight: Int,
    graphicsCardLength: Int,
    imageUrl: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false, unique = true)
    var name: String = name
        protected set

    @Column(nullable = false)
    var manufacturer: String = manufacturer
        protected set

    @Column(nullable = false)
    var caseType: String = caseType
        protected set

    @Column(nullable = false)
    var motherBoardFormFactor: String = motherBoardFormFactor
        protected set

    @Column(nullable = false)
    var maxCoolingFan: Int = maxCoolingFan
        protected set

    @Column(nullable = false)
    var builtInCoolingFan: Int = builtInCoolingFan
        protected set

    @Column(nullable = false)
    var height: Int = height
        protected set

    @Column(nullable = false)
    var length: Int = length
        protected set

    @Column(nullable = false)
    var width: Int = width
        protected set

    @Column(nullable = false)
    var powerLength: Int = powerLength
        protected set

    @Column(nullable = false)
    var cpuCoolerHeight: Int = cpuCoolerHeight
        protected set

    @Column(nullable = false)
    var graphicsCardLength: Int = graphicsCardLength
        protected set

    @Column(nullable = false)
    var imageUrl: String = imageUrl
        protected set

    fun update(caseDTO: CaseDTO) {
        this.name = caseDTO.name
        this.manufacturer = caseDTO.manufacturer
        this.caseType = caseDTO.caseType
        this.motherBoardFormFactor = caseDTO.motherBoardFormFactor
        this.maxCoolingFan = caseDTO.maxCoolingFan
        this.builtInCoolingFan = caseDTO.builtInCoolingFan
        this.height = caseDTO.height
        this.length = caseDTO.length
        this.width = caseDTO.width
        this.powerLength = caseDTO.powerLength
        this.cpuCoolerHeight = caseDTO.cpuCoolerHeight
        this.graphicsCardLength = caseDTO.graphicsCardLength
        if(caseDTO.imageUrl!=null)
            this.imageUrl = caseDTO.imageUrl
    }
}
