package com.example.cusCom.provideContent.entity.parts

import com.example.cusCom.provideContent.dto.parts.CPU
import jakarta.persistence.*

@Table(name="cpu")
@Entity
class CPUEntity(cpu: CPU){

    @Id
    @Column(nullable = false)
    var name:String=cpu.name
        protected set

    @Column(nullable=false)
    var imageUrl:String=cpu.imageUrl
        protected set

    @Column(nullable = false)
    var manufacturer:String=cpu.manufacturer
        protected set

    @Column(nullable = false)
    var socket:String=cpu.socket
        protected set

    @Column(nullable = false)
    var memoryType:String=cpu.memoryType
        protected set

    @Column(nullable = false)
    var core:Int=cpu.core
        protected set

    @Column(nullable = false)
    var thread:Int=cpu.thread
        protected set

    @Column(nullable = false)
    var isBuiltInGraphics:Boolean=cpu.isBuiltInGraphics
        protected set

    @Column
    var builtInGraphicName:String?=cpu.builtInGraphicName
        protected set

    @Column(nullable = false)
    var TDP:Int=cpu.TDP
        protected set

    fun update(cpu: CPU){
        this.name=cpu.name
        this.imageUrl=cpu.imageUrl
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
