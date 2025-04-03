package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.dto.parts.CpuCoolerDTO
import jakarta.persistence.*

@Entity
@Table(name = "cpu_cooler")
class CPUCoolerEntity(
    name: String,
    imageUrl: String,
    manufacturer: String,
    coolingType: String,
    coolerForm: String,
    height: Int,
    length: Int,
    width: Int,
    tdp: Int
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
    var coolingType: String = coolingType
        protected set

    @Column(nullable = false)
    var coolerForm: String = coolerForm
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
    var tdp: Int = tdp
        protected set

    fun update(cpuCoolerDTO: CpuCoolerDTO) {
        this.name = cpuCoolerDTO.name
        if(cpuCoolerDTO.imageUrl!=null)
            this.imageUrl = cpuCoolerDTO.imageUrl
        this.manufacturer = cpuCoolerDTO.manufacturer
        this.coolingType = cpuCoolerDTO.coolingType
        this.coolerForm = cpuCoolerDTO.coolerForm
        this.height = cpuCoolerDTO.height
        this.length = cpuCoolerDTO.length
        this.width = cpuCoolerDTO.width
        this.tdp = cpuCoolerDTO.tdp
    }
}
