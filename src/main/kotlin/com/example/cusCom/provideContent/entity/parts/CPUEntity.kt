package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.CPU
import jakarta.persistence.*

@Table(name="cpu")
@Entity
class CPUEntity(cpu: CPU){
    @Id
    @Column(nullable = false)
    var name:String=cpu.name
    @Column(nullable = false)
    var manufacturer:String=cpu.manufacturer
    @Column(nullable = false)
    var socket:String=cpu.socket
    @Column(nullable = false)
    var memoryType:String=cpu.memoryType
    @Column(nullable = false)
    var core:Int=cpu.core
    @Column(nullable = false)
    var thread:Int=cpu.thread
    @Column(nullable = false)
    var isBuiltInGraphics:Boolean=cpu.isBuiltInGraphics
    @Column
    var builtInGraphicName:String?=cpu.builtInGraphicName
    @Column(nullable = false)
    var TDP:Int=cpu.TDP

    fun update(cpu: CPU){
        this.name=cpu.name
        this.manufacturer=cpu.manufacturer
        this.socket=cpu.socket
        this.memoryType=cpu.memoryType
        this.core=cpu.core
        this.thread=cpu.thread
        this.isBuiltInGraphics=cpu.isBuiltInGraphics
        this.builtInGraphicName=cpu.builtInGraphicName
        this.TDP=TDP
    }
}
