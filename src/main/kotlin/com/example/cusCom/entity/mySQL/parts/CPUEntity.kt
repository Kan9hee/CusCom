package com.example.cusCom.entity.mySQL.parts

import com.example.cusCom.dto.parts.CpuDTO
import jakarta.persistence.*

@Entity
@Table(name = "cpu")
class CPUEntity(
    name: String,
    imageUrl: String,
    manufacturer: String,
    socket: String,
    memoryType: String,
    core: Int,
    thread: Int,
    isBuiltInGraphics: Boolean,
    builtInGraphicName: String?,
    tdp:Int
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
    var socket: String = socket
        protected set

    @Column(nullable = false)
    var memoryType: String = memoryType
        protected set

    @Column(nullable = false)
    var core: Int = core
        protected set

    @Column(nullable = false)
    var thread: Int = thread
        protected set

    @Column(nullable = false)
    var isBuiltInGraphics: Boolean = isBuiltInGraphics
        protected set

    @Column
    var builtInGraphicName: String? = builtInGraphicName
        protected set

    @Column(nullable = false)
    var tdp: Int = tdp
        protected set

    fun update(cpuDTO: CpuDTO) {
        this.name = cpuDTO.name
        if(cpuDTO.imageUrl!=null)
            this.imageUrl = cpuDTO.imageUrl
        this.manufacturer = cpuDTO.manufacturer
        this.socket = cpuDTO.socket
        this.memoryType = cpuDTO.memoryType
        this.core = cpuDTO.core
        this.thread = cpuDTO.thread
        this.isBuiltInGraphics = cpuDTO.isBuiltInGraphics
        this.builtInGraphicName = cpuDTO.builtInGraphicName
        this.tdp = cpuDTO.tdp
    }
}
