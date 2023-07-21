package com.example.cusCom.estimate.dto.parts

import jakarta.persistence.*

@Table(name="cpu")
@Entity
class CPUEntity(name:String,
                manufacturer:String,
                socket:String,
                memoryType:String,
                core:Int,
                thread:Int,
                isBuiltInGraphics:Boolean,
                builtInGraphicName:String?,
                TDP:Int){
    @Id
    @Column(nullable = false)
    var name:String=name
    @Column(nullable = false)
    var manufacturer:String=manufacturer
    @Column(nullable = false)
    var socket:String=socket
    @Column(nullable = false)
    var memoryType:String=memoryType
    @Column(nullable = false)
    var core:Int=core
    @Column(nullable = false)
    var thread:Int=thread
    @Column(nullable = false)
    var isBuiltInGraphics:Boolean=isBuiltInGraphics
    @Column
    var builtInGraphicName:String?=builtInGraphicName
    @Column(nullable = false)
    var TDP:Int=TDP

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
