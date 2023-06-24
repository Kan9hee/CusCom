package com.example.cusCom.estimate.model.parts

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
                builtInGraphicName:String,
                TDP:Int){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null

    @Column(nullable = false, unique = true)
    val name:String=name
    @Column(nullable = false)
    val manufacturer:String=manufacturer
    @Column(nullable = false)
    val socket:String=socket
    @Column(nullable = false)
    val memoryType:String=memoryType
    @Column(nullable = false)
    val core:Int=core
    @Column(nullable = false)
    val thread:Int=thread
    @Column(nullable = false)
    val isBuiltInGraphics:Boolean=isBuiltInGraphics
    @Column(nullable = false)
    val builtInGraphicName:String=builtInGraphicName
    @Column(nullable = false)
    val TDP:Int=TDP
}
